namespace FinFlowAPI.Models
{
    public class Account
    {
        public int Id { get; set; }
        public string Name { get; set; } = "";
        public double Balance { get; set; }
        public Currency Currency { get; set; } = new Currency();
        public string AccountType { get; set; } = "";
    }
}
