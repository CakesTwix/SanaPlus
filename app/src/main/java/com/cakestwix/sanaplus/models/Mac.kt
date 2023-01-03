import com.google.gson.annotations.SerializedName

data class Mac (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)