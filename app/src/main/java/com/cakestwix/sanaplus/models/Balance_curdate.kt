import com.google.gson.annotations.SerializedName

data class Balance_curdate (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)