import com.google.gson.annotations.SerializedName

data class Inet (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String,
	@SerializedName("mac") val mac : Mac,
	@SerializedName("packet") val packet : Packet,
	@SerializedName("summa") val summa : Summa,
	@SerializedName("status") val status : Status
)