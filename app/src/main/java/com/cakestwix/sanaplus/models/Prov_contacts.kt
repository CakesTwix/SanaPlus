import com.google.gson.annotations.SerializedName

data class Prov_contacts (

	@SerializedName("messenger") val messenger : Messenger,
	@SerializedName("mailTo") val mailTo : MailTo,
	@SerializedName("questions") val questions : Questions,
	@SerializedName("police") val police : Police,
	@SerializedName("phones") val phones : Phones,
	@SerializedName("addresses") val addresses : Addresses
)