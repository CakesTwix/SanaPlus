import com.google.gson.annotations.SerializedName

data class Email (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)