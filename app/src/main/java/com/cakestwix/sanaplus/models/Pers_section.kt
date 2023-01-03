import com.google.gson.annotations.SerializedName

data class Pers_section (

	@SerializedName("fio") val fio : Fio,
	@SerializedName("address") val address : Address,
	@SerializedName("id") val id : Id,
	@SerializedName("email") val email : Email,
	@SerializedName("phone") val phone : Phone,
	@SerializedName("super") val super_ : Int
)