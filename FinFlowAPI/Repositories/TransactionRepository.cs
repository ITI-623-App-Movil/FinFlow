using FinFlowAPI.Models;

namespace FinFlowAPI.Repositories
{
    public class TransactionRepository : Repository<Transaction>
    {
        public TransactionRepository() : base("Data/transactions.json") { }
    }
}
