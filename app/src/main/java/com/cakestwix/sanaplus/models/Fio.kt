import com.google.gson.annotations.SerializedName

data class Fio (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)