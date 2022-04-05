package cat.api.apifromscratch.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthorData(
    @Expose
    @SerializedName("id") val idAutor: Int?,

    @Expose
    @SerializedName("nombre") val nomAutor: String?
)