package cat.api.apifromscratch.api

import cat.api.apifromscratch.api.models.AuthorData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiInterface {

    @GET("autores")
    fun getAllAuthors(): Call<List<AuthorData>>

    @GET("autores/{id}")
    fun getAuthorById(
        @Path("id") number: Int
    ): Call<AuthorData>

    @DELETE("/autores/{id}")
    fun deleteAuthor(
        @Path("id") id: Int
    ): Call<AuthorData?>

    @Headers("Content-Type: application/json")
    @POST("autores")
    fun postAutor(@Body authorData: AuthorData): Call<AuthorData>

    companion object {

        var BASE_URL = "http://10.0.2.2:8081/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}