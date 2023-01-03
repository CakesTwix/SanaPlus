import com.google.gson.annotations.SerializedName

data class Addresses (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : List<Value>
)