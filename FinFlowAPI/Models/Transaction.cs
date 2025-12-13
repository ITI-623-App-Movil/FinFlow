namespace FinFlowAPI.Models
{
    public class Transaction
    {
        public int Id { get; set; }
        public double Amount { get; set; }
        public string Description { get; set; } = "";
        public DateTime Date { get; set; }
        public Category Category { get; set; } = new Category();
        public Account Account { get; set; } = new Account();
    }
}
