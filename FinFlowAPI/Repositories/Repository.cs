using System.Text.Json;

namespace FinFlowAPI.Repositories
{
    public abstract class Repository<T> : IRepository<T>
    {
        protected readonly string FilePath;
        protected List<T> Items;

        protected Repository(string filePath)
        {
            FilePath = filePath;
            Items = Load();
        }

        private List<T> Load()
        {
            if (!File.Exists(FilePath))
                return new List<T>();

            string json = File.ReadAllText(FilePath);
            return JsonSerializer.Deserialize<List<T>>(json) ?? new List<T>();
        }

        protected void Save()
        {
            string json = JsonSerializer.Serialize(Items, new JsonSerializerOptions { WriteIndented = true });
            File.WriteAllText(FilePath, json);
        }

        public List<T> GetAll() => Items;

        public T? GetById(int id)
        {
            return Items.FirstOrDefault(item =>
                (int)item!.GetType().GetProperty("Id")!.GetValue(item)! == id);
        }

        public T Add(T entity)
        {
            int newId = Items.Count == 0 ? 1 :
                Items.Max(x => (int)x!.GetType().GetProperty("Id")!.GetValue(x)!) + 1;

            entity!.GetType().GetProperty("Id")!.SetValue(entity, newId);

            Items.Add(entity);
            Save();
            return entity;
        }

        public T Update(int id, T entity)
        {
            var existing = GetById(id);
            if (existing == null)
                throw new Exception("Item not found");

            int index = Items.IndexOf(existing);
            entity!.GetType().GetProperty("Id")!.SetValue(entity, id);
            Items[index] = entity;

            Save();
            return entity;
        }

        public bool Delete(int id)
        {
            var entity = GetById(id);
            if (entity == null) return false;

            Items.Remove(entity);
            Save();
            return true;
        }
    }
}
