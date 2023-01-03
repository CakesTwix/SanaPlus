import com.google.gson.annotations.SerializedName

data class Serv_section (

	@SerializedName("period") val period : String,
	@SerializedName("summa_all") val summa_all : Int,
	@SerializedName("tv") val tv : String,
	@SerializedName("inet") val inet : Inet
)