using FinFlowAPI.Models;

namespace FinFlowAPI.Repositories
{
    public class AccountRepository : Repository<Account>
    {
        public AccountRepository() : base("Data/accounts.json") { }
    }
}
