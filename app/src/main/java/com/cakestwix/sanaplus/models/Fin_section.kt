import com.google.gson.annotations.SerializedName

data class Fin_section (

	@SerializedName("status") val status : Status,
	@SerializedName("balance_curdate") val balance_curdate : Balance_curdate,
	@SerializedName("date_stop") val date_stop : Date_stop,
	@SerializedName("balance_1") val balance_1 : Balance_1,
	@SerializedName("credit") val credit : Credit,
	@SerializedName("payed") val payed : Payed,
	@SerializedName("used") val used : Used
)