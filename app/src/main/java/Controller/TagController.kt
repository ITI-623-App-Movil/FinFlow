package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Tag
import android.content.Context
import kgpa.finflow.R

class TagController {
    private var dataManager: IDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addTag(tag: Tag) {
        try {
            dataManager.addTag(tag)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateTag(tag: Tag) {
        try {
            dataManager.updateTag(tag)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getTag(): List<Tag> {
        try {
            return dataManager.getAllTag()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: Int) : Tag {
        try {
            val result = dataManager.getByIdTag(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByName(name: String) : Tag {
        try {
            val result = dataManager.getByNameTag(name)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }
}