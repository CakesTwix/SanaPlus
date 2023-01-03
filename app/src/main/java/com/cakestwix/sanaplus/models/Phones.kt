import com.google.gson.annotations.SerializedName

data class Phones (

	@SerializedName("name") val name : String,
	@SerializedName("operators") val operators : List<Operators>
)