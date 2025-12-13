using FinFlowAPI.Models;

namespace FinFlowAPI.Repositories
{
    public class CurrencyRepository : Repository<Currency>
    {
        public CurrencyRepository() : base("Data/currency.json") { }
    }
}
