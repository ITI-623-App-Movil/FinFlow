using FinFlowAPI.Models;
using FinFlowAPI.Repositories;
using Microsoft.AspNetCore.Mvc;

namespace FinFlowAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CurrencyController : ControllerBase
    {
        private readonly CurrencyRepository _repo = new();

        [HttpGet]
        public ActionResult<List<Currency>> GetAll() => _repo.GetAll();

        [HttpGet("{id}")]
        public ActionResult<Currency> Get(int id)
        {
            var item = _repo.GetById(id);
            return item is null ? NotFound() : Ok(item);
        }

        [HttpPost]
        public ActionResult<Currency> Create(Currency currency)
        {
            var created = _repo.Add(currency);
            return CreatedAtAction(nameof(Get), new { id = created.Id }, created);
        }

        [HttpPut("{id}")]
        public ActionResult<Currency> Update(int id, Currency currency)
        {
            try
            {
                return Ok(_repo.Update(id, currency));
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
