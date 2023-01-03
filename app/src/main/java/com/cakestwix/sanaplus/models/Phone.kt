import com.google.gson.annotations.SerializedName

data class Phone (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : Int
)