import com.google.gson.annotations.SerializedName

data class Status (

	@SerializedName("active") val active : Boolean,
	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)