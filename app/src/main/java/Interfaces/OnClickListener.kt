package Interfaces

import Entity.Transaction

interface OnItemClickListener {
    fun onItemClicked (transaction: Transaction)
}