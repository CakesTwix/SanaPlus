import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("pers_section") val pers_section : Pers_section,
	@SerializedName("fin_section") val fin_section : Fin_section,
	@SerializedName("serv_section") val serv_section : Serv_section,
	@SerializedName("prov_contacts") val prov_contacts : Prov_contacts
)
