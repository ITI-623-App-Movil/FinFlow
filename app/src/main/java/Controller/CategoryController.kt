package Controller;

import Entity.Category
import Entity.CategoryDTO
import Services.APIService
import android.content.Context
import kgpa.finflow.R

class CategoryController {
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    suspend fun getCategory(): List<Category> {
        var categories = mutableListOf<Category>()

        try {
            val response = APIService.apiCategory.getCategory()

            response.forEach { item ->
                categories.add(getCategoryObject(item))
            }
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }

        return categories
    }

    suspend fun getById(id: Int): Category? {
        try {
            val response = APIService.apiCategory.getById(id)
            return getCategoryObject(response)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    suspend fun addCategory(category: Category) {
        try {
            APIService.apiCategory.addCategory(getCategoryDTO(category))

        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    suspend fun updateCategory(category: Category) {
        try {
            APIService.apiCategory.updateCategory(category.ID, getCategoryDTO(category))
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    suspend fun removeCategory(id: Int) {
        try {
            APIService.apiCategory.deleteCategory(id)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    private fun getCategoryObject(item: CategoryDTO): Category {
        var category = Category()
        category.ID = item.ID
        category.Type = item.Type
        category.Name = item.Name

        return category
    }

    private fun getCategoryDTO(category: Category): CategoryDTO {
        var categoryDTO = CategoryDTO(category.ID, category.Type, category.Name)
        return categoryDTO
    }
}