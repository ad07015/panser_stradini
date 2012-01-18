using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using InventoryDataAssembly;

namespace InventoryWCFAssembly
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IUserService" in both code and config file together.
    [ServiceContract]
    public interface IUserService
    {
        [OperationContract]
        bool logIn(String fname, String lname);

        [OperationContract]
        bool addPart(String id, String descr, double price, int count);

        [OperationContract]
        bool reservePart(String id, int count);

        [OperationContract]
        List<Inventory> searchPartByDescription(String descr);

        [OperationContract]
        List<Inventory> getAllParts();

        [OperationContract]
        double calculateTotal();

        [OperationContract]
        [TransactionFlow(TransactionFlowOption.Allowed)]
        double calculateReserved();

        [OperationContract]
        [TransactionFlow(TransactionFlowOption.Allowed)]
        double calculateBalance();
    }
}
