using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using InventoryIAssembly;
using System.Data.Linq.SqlClient;

namespace InventoryAssembly
{
    public class UserSess : IUserSessInterface
    {
        internal static InventoryDataClassesDataContext inventoryDataContext;
        
        public UserSess()
        {
            inventoryDataContext = new InventoryDataClassesDataContext();
        }

        public bool logIn(String fname, String lname)
        {
            try
            {

                var usersData = from usr in inventoryDataContext.Users
                                where usr.LAST_NAME == lname
                                select usr;

                if (usersData.Count() == 0)
                {
                    //Create a new user object.
                    User newUser = new User();
                    newUser.FIRST_NAME = fname;
                    newUser.LAST_NAME = lname;
                    inventoryDataContext.Users.InsertOnSubmit(newUser);
                    inventoryDataContext.SubmitChanges();
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return true;
        }


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
                inventoryDataContext.Inventories.InsertOnSubmit(newInventory);
             }
            else
            { //add count
                inventoryData.First().INSTOCK = inventoryData.First().INSTOCK + count;
            }
            inventoryDataContext.SubmitChanges();
            return true;
        }


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
            inventoryDataContext.SubmitChanges();
            return true;
        }

        public InventoryDTO[] searchPartByDescription(String descr)
        {
            var inventoryData = from inv in inventoryDataContext.Inventories
                                where SqlMethods.Like(inv.DESCRIPTION, descr)
                                select inv;

            InventoryDTO[] tmpDTOArray = new InventoryDTO[inventoryData.Count()];
            int i = 0;
            foreach (Inventory currInv in inventoryData)
            {
                InventoryDTO tmpDTO = new InventoryDTO();
                tmpDTO.setId(currInv.ID);
                tmpDTO.setPrice((double)currInv.PRICE);
                tmpDTO.setDescription(currInv.DESCRIPTION);
                tmpDTO.setInstock((int)currInv.INSTOCK);
                tmpDTO.setReserved((int)currInv.RESERVED);
                tmpDTOArray[i++] = tmpDTO;
            }
            return tmpDTOArray;
        }


        public InventoryDTO[] getAllParts()
        {
            var inventoryData = from inv in inventoryDataContext.Inventories
                                select inv;

            InventoryDTO[] tmpDTOArray = new InventoryDTO[inventoryData.Count()];
            int i = 0;
            foreach (Inventory currInv in inventoryData)
            {
                InventoryDTO tmpDTO = new InventoryDTO();
                tmpDTO.setId(currInv.ID);
                tmpDTO.setPrice((double) currInv.PRICE);
                tmpDTO.setDescription(currInv.DESCRIPTION);
                tmpDTO.setInstock((int) currInv.INSTOCK);
                tmpDTO.setReserved((int) currInv.RESERVED);
                tmpDTOArray[i++] = tmpDTO;
            }
            return tmpDTOArray;
        }


        public double calculateTotal()
        {
            return (double)(from inv in inventoryDataContext.Inventories
                            select inv.PRICE*inv.INSTOCK).Sum();
        }

        public double calculateReserved()
        {
            return (double)(from inv in inventoryDataContext.Inventories
                            select inv.PRICE * inv.RESERVED).Sum();
        }


        public double calculateBalance()
        {
            return calculateTotal() - calculateReserved();
        }


 

 

    }

}
