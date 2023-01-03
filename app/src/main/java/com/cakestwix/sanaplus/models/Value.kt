import com.google.gson.annotations.SerializedName

data class Value (

	@SerializedName("name") val name : String,
	@SerializedName("link") val link : String
)