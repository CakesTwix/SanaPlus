import com.google.gson.annotations.SerializedName

data class Payed (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)