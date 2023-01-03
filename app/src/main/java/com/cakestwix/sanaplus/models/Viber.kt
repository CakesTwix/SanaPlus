import com.google.gson.annotations.SerializedName

data class Viber (

	@SerializedName("regularText") val regularText : String,
	@SerializedName("link") val link : String
)