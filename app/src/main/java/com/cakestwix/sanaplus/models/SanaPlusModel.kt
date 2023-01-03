import com.google.gson.annotations.SerializedName

data class SanaPlusModel (

	@SerializedName("result") val result : String,
	@SerializedName("data") val data : Data
)
