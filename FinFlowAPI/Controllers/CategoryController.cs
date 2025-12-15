using FinFlowAPI.Models;
using FinFlowAPI.Repositories;
using Microsoft.AspNetCore.Mvc;

namespace FinFlowAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CategoryController : ControllerBase
    {
        private readonly CategoryRepository _repo = new();

        [HttpGet]
        public ActionResult<List<Category>> GetAll() => _repo.GetAll();

        [HttpGet("{id}")]
        public ActionResult<Category> Get(int id)
        {
            var item = _repo.GetById(id);
            return item is null ? NotFound() : Ok(item);
        }

        [HttpPost]
        public ActionResult<Category> Create(Category category)
        {
            var created = _repo.Add(category);
            return CreatedAtAction(nameof(Get), new { id = created.Id }, created);
        }

        [HttpPut("{id}")]
        public ActionResult<Category> Update(int id, Category category)
        {
            try
            {
                return Ok(_repo.Update(id, category));
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
