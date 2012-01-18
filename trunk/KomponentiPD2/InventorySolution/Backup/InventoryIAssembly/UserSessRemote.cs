using System;
using System.Collections.Generic;
using System.Text;

namespace InventoryIAssembly
{
    public interface IUserSessInterface
    {
        bool logIn(String fname, String lname);
        bool addPart(String id, String descr, double price, int count);
        bool reservePart(String id, int count);
        InventoryDTO[] searchPartByDescription(String descr);
        InventoryDTO[] getAllParts();
        double calculateTotal();
        double calculateReserved();
        double calculateBalance();
    }

    [Serializable]
    public class InventoryDTO
    {
        private String id;
        private double price;
        private String description;
        private int instock;
        private int reserved;

        public InventoryDTO()
		{
			//
			// TODO: Add constructor logic here
			//
		}

        public void setId(String str)
        {
            id = str;
        }
        public String getId()
        {
            return id;
        }

        public void setPrice(double dbl)
        {
            price = dbl;
        }

        public double getPrice()
        {
            return price;
        }

        public void setDescription(String descr)
        {
            description = descr;
        }
        public String getDescription()
        {
            return description;
        }

        public void setInstock(int intval)
        {
            instock = intval;
        }
        public int getInstock()
        {
            return instock;
        }

        public void setReserved(int intval)
        {
            reserved = intval;
        }
        public int getReserved()
        {
            return reserved;
        }        
    }

}
