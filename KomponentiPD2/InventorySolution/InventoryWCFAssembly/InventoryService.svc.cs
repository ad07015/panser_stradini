using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using InventoryDataAssembly;

namespace InventoryWCFAssembly
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]
    public class InventoryService : IInventoryService
    {
        //[OperationBehavior(TransactionScopeRequired = true)]
        public double calculateTotal()
        {
            using (InventoryEntities inventoryDataContext = new InventoryEntities())
            {
                return (double)(from inv in inventoryDataContext.Inventories
                                select inv.PRICE * inv.INSTOCK).Sum();
            }
        }

        public double calculateReserved()
        {
            using (InventoryEntities inventoryDataContext = new InventoryEntities())
            {
                return (double)(from inv in inventoryDataContext.Inventories
                                select inv.PRICE * inv.RESERVED).Sum();  
            }
        }

        public double calculateBalance()
        {
            return calculateTotal() - calculateReserved();
        }
    }
}
