import com.google.gson.annotations.SerializedName

data class Packet (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String,
	@SerializedName("info") val info : String
)