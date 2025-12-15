using FinFlowAPI.Models;

namespace FinFlowAPI.Repositories
{
    public class CategoryRepository : Repository<Category>
    {
        public CategoryRepository() : base("Data/categories.json") { }
    }
}
