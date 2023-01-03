import com.google.gson.annotations.SerializedName

data class Used (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)