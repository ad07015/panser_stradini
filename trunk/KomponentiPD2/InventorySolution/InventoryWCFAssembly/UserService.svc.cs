using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using InventoryDataAssembly;

namespace InventoryWCFAssembly
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerSession)]
    public class UserService : IUserService
    {
        internal static InventoryEntities inventoryDataContext = new InventoryEntities();

        [OperationBehavior(TransactionScopeRequired = true)]
        public bool logIn(String fname, String lname)
        {
            if (fname == null || lname == null || fname == "" || lname == "")
                return false;

            var usersData = from usr in inventoryDataContext.Users
                            where usr.LAST_NAME == lname
                            select usr;

            if (usersData.Count() == 0)
            {
                //Create a new user object.
                User newUser = new User();
                newUser.FIRST_NAME = fname;
                newUser.LAST_NAME = lname;
                inventoryDataContext.Users.AddObject(newUser);
                inventoryDataContext.SaveChanges();
            }

            return true;
        }

        [OperationBehavior(TransactionScopeRequired = true)]
        public bool addPart(String id, String descr, double price, int count)
        {
            if (id == null || id.Equals(""))
                return false;

            var inventoryData = from inv in inventoryDataContext.Inventories
                                where inv.ID == id
                                select inv;

            if (inventoryData.Count() == 0)
            { //create a new inventory item
                Inventory newInventory = new Inventory();
                newInventory.ID = id;
                newInventory.DESCRIPTION = descr;
                newInventory.PRICE = price;
                newInventory.INSTOCK = count;
                newInventory.RESERVED = 0;
                inventoryDataContext.Inventories.AddObject(newInventory);
            }
            else
            { //add count
                inventoryData.First().INSTOCK = inventoryData.First().INSTOCK + count;
            }
            inventoryDataContext.SaveChanges();
            return true;
        }

        [OperationBehavior(TransactionScopeRequired = true)]
        public bool reservePart(String id, int count)
        {
            if (id == null || id.Equals(""))
                return false;

            var inventoryData = from inv in inventoryDataContext.Inventories
                                where inv.ID == id
                                select inv;

            if (inventoryData.Count() == 0)
                return false;

            inventoryData.First().RESERVED = inventoryData.First().RESERVED + count;
            inventoryDataContext.SaveChanges();
            return true;
        }

        public List<Inventory> searchPartByDescription(String descr)
        {
            return (from inv in inventoryDataContext.Inventories
                    where inv.DESCRIPTION.Contains(descr)
                    select inv).ToList(); 
        }


        public List<Inventory> getAllParts()
        {
            return inventoryDataContext.Inventories.OrderBy(c => c.DESCRIPTION).ToList();
        }

        public double calculateTotal()
        {
            IInventoryService tmpInvService = new InventoryService();
            return tmpInvService.calculateTotal();
        }

        public double calculateReserved()
        {
            IInventoryService tmpInvService = new InventoryService();
            return tmpInvService.calculateReserved();
        }

        public double calculateBalance()
        {
            IInventoryService tmpInvService = new InventoryService();
            return tmpInvService.calculateBalance();
        }
    }
}
