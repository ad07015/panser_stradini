using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using InventoryDataAssembly;

namespace InventoryWCFAssembly
{
    [ServiceContract]
    public interface IInventoryService
    {
        [OperationContract]
//      [TransactionFlow(TransactionFlowOption.Allowed)]
        double calculateTotal();

        [OperationContract]
        double calculateReserved();

        [OperationContract]
        double calculateBalance();
    }
}

// add additional data contracts ....
//[DataContract()]
//public class AuxClass
//{
//}
