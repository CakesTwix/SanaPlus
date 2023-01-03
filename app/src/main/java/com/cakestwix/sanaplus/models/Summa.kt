import com.google.gson.annotations.SerializedName

data class Summa (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : Double
)