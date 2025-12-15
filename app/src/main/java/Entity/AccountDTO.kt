package Entity

import com.google.gson.annotations.SerializedName

data class AccountDTO (
    @SerializedName("id") val ID: Int,
    @SerializedName("name") val Name: String,
    @SerializedName("balance") val Balance: Double,
    @SerializedName("currency") val Currency: Currency,
    @SerializedName("accountType") val AccountType: String
)
