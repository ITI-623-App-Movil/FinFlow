namespace FinFlowAPI.Repositories
{
    public interface IRepository<T>
    {
        List<T> GetAll();
        T? GetById(int id);
        T Add(T entity);
        T Update(int id, T entity);
        bool Delete(int id);
    }
}
