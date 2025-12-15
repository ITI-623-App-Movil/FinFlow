package Entity

import com.google.gson.annotations.SerializedName

data class CurrencyDTO(
    @SerializedName("id") val ID: Int,
    @SerializedName("code") val Code: String,
    @SerializedName("name") val Name: String
)
