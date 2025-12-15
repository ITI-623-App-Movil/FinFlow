package kgpa.finflow

import Entity.Transaction
import Interfaces.OnItemClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomViewHolder (view: View): RecyclerView.ViewHolder(view){
    var txtTransactionDescription: TextView = view.findViewById(R.id.txtTransactionDescriptionItem_recycler)

    var txtTransactionAmount: TextView = view.findViewById(R.id.txtTransactionAmountItem_recycler)

    var txtCategory: TextView = view.findViewById(R.id.txtCategoryItem_recycler)

    var txtAccount: TextView = view.findViewById(R.id.txtAccountItem_recycler)

    var txtTransactionDate: TextView = view.findViewById(R.id.txtTransactionDateItem_recycler)

    fun bind (item: Transaction, clickListener: OnItemClickListener) {
        val currencyCode = try {
            item.Account.Currency.Code
        } catch (e: Exception) {
            "N/A"
        }
        txtTransactionDescription.setText(item.Description)
        txtTransactionAmount.setText(item.Amount.toString())
        txtCategory.setText("${item.Category.Type} - ${item.Category.Name}")
        txtAccount.setText("${item.Account.Name} - ${currencyCode}")
        txtTransactionDate.setText(Util.Util.parseDateToString(item.Date,
            "dd/MM/yyyy"))

        itemView.setOnClickListener{
            clickListener.onItemClicked(item)
        }
    }
}

class TransactionAdapter (private var itemList: List<Transaction>,
    val itemClickListener: OnItemClickListener): RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_transaction_item, parent, false
        )
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var item = itemList[position]
        holder.bind(item, itemClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}