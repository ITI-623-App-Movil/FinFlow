using FinFlowAPI.Models;
using FinFlowAPI.Repositories;
using Microsoft.AspNetCore.Mvc;

namespace FinFlowAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AccountController : ControllerBase
    {
        private readonly AccountRepository _repo = new();

        [HttpGet]
        public ActionResult<List<Account>> GetAll() => _repo.GetAll();

        [HttpGet("{id}")]
        public ActionResult<Account> Get(int id)
        {
            var item = _repo.GetById(id);
            return item is null ? NotFound() : Ok(item);
        }

        [HttpPost]
        public ActionResult<Account> Create(Account account)
        {
            var created = _repo.Add(account);
            return CreatedAtAction(nameof(Get), new { id = created.Id }, created);
        }

        [HttpPut("{id}")]
        public ActionResult<Account> Update(int id, Account account)
        {
            try
            {
                return Ok(_repo.Update(id, account));
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
