using FinFlowAPI.Models;
using FinFlowAPI.Repositories;
using Microsoft.AspNetCore.Mvc;

namespace FinFlowAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TransactionController : ControllerBase
    {
        private readonly TransactionRepository _repo = new();

        [HttpGet]
        public ActionResult<List<Transaction>> GetAll() => _repo.GetAll();

        [HttpGet("{id}")]
        public ActionResult<Transaction> Get(int id)
        {
            var item = _repo.GetById(id);
            return item is null ? NotFound() : Ok(item);
        }

        [HttpPost]
        public ActionResult<Transaction> Create(Transaction transaction)
        {
            var created = _repo.Add(transaction);
            return CreatedAtAction(nameof(Get), new { id = created.Id }, created);
        }

        [HttpPut("{id}")]
        public ActionResult<Transaction> Update(int id, Transaction transaction)
        {
            try
            {
                return Ok(_repo.Update(id, transaction));
            }
            catch
            {
                return NotFound();
            }
        }

        [HttpDelete("{id}")]
        public ActionResult Delete(int id)
        {
            return _repo.Delete(id) ? NoContent() : NotFound();
        }
    }
}
