import com.google.gson.annotations.SerializedName

data class Telegram (

	@SerializedName("regularText") val regularText : String,
	@SerializedName("link") val link : String
)