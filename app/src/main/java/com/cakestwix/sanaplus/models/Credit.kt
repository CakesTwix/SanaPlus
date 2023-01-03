import com.google.gson.annotations.SerializedName

data class Credit (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : Double
)