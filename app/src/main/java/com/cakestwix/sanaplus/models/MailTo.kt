import com.google.gson.annotations.SerializedName

data class MailTo (

	@SerializedName("title") val title : String,
	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)