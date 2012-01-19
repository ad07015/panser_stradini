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
        public User logIn(String fname, String lname)
        {
            if (fname == null || lname == null || fname == "" || lname == "")
                return null;

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
                return newUser;
            }

            return usersData.First();
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

        public OrderItem findOrderItemById(string id)
        {
            using (InventoryEntities inventoryDataContext = new InventoryEntities())
            {
                return (OrderItem)(from oit in inventoryDataContext.OrderItems
                                   where oit.INVENTORY_FK == id
                                   select oit);
            }

        }

        public bool addOrderItem(Inventory inventory, User user, int count)
        {
            if (inventory == null || user == null)
                return false;

            var inventoryData = from oit in inventoryDataContext.OrderItems
                                where oit.INVENTORY_FK == inventory.ID && oit.USER_FK == user.ID
                                select oit;

            if (inventoryData.Count() == 0)
            { //create a new order item
                OrderItem newOrderItem = new OrderItem();
                //newOrderItem.ID = 123;
                newOrderItem.INVENTORY_FK = inventory.ID;
                newOrderItem.USER_FK = user.ID;
                //newOrderItem.INVENTORY_TABLE = inventory;
                //newOrderItem.USERS_TABLE = user;
                newOrderItem.COUNT = count;
                inventoryDataContext.OrderItems.AddObject(newOrderItem);
                //inventoryDataContext.OrderItems.ApplyCurrentValues(newOrderItem);
            }
            else
            { //add count
                inventoryData.First().COUNT = inventoryData.First().COUNT + count;
            }
            try
            {
                inventoryDataContext.SaveChanges();
            }
            catch (Exception e)
            {
                Console.WriteLine("{0} Exception caught.", e);
            }
            return true;
        }

        public User getCurrentUser(string firstName, string lastName)
        {
            using (InventoryEntities inventoryDataContext = new InventoryEntities())
            {
                try
                {
                    List<User> currentUserList = (from use in inventoryDataContext.Users
                                                  where use.FIRST_NAME == firstName && use.LAST_NAME == lastName
                                                  select use).ToList<User>();
                    User currentUser = currentUserList.First();
                    Console.WriteLine("Curren user ID =" + currentUser.ID);
                    return currentUser;
                }
                catch (Exception e)
                {
                    Console.WriteLine("{0} Exception caught.", e);
                }
                return null;
            }
        }

        public List<OrderItem> getShoppingCart(int currentUserId)
        {
            List<OrderItem> orderItemList = (from oit in inventoryDataContext.OrderItems
                    where oit.USER_FK == currentUserId
                    select oit).ToList();
            return orderItemList;
        }
    }
}
