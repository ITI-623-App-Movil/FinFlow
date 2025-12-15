package Entity

import com.google.gson.annotations.SerializedName

data class TransactionDTO(
    @SerializedName("id") val ID: Int,
    @SerializedName("amount") val Amount: Double,
    @SerializedName("description") val Description: String,
    @SerializedName("date") val Date: String,
    @SerializedName("category") val Category: CategoryDTO,
    @SerializedName("account") val Account: AccountDTO
)
