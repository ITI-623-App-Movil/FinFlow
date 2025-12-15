namespace FinFlowAPI.Models
{
    public class Category
    {
        public int Id { get; set; }
        public string Type { get; set; } = ""; // Income o Expense
        public string Name { get; set; } = "";
    }
}
