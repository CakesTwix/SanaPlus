import com.google.gson.annotations.SerializedName

data class Messenger (

	@SerializedName("name") val name : String,
	@SerializedName("Viber") val viber : Viber,
	@SerializedName("Telegram") val telegram : Telegram
)