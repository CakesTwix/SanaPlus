import com.google.gson.annotations.SerializedName

data class Address (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)