import com.google.gson.annotations.SerializedName

data class Police (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String,
	@SerializedName("link") val link : String
)