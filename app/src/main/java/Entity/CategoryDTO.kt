package Entity

import com.google.gson.annotations.SerializedName

data class CategoryDTO (
    @SerializedName("id") val ID: Int,
    @SerializedName("type") val Type: String,
    @SerializedName("name") val Name: String
)
