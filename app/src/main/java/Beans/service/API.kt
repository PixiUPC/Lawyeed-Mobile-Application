package Beans.service

import Beans.service.`class`.*
import retrofit2.Call
import retrofit2.http.*

interface API {
     @GET
     fun getCases(@Url url:String): Call<List<CasesResponse>>
     @GET
     fun getNotifications(@Url url:String): Call<List<NotificationResponse>>
     @GET
     fun getLawyers(@Url url:String): Call<List<PersonResponse>>
     @GET
     fun getOneCase(@Url url: String): Call<OneCaseReponse>

     @GET
     fun login(@Url url: String):Call<PersonResponse>
     @GET
     fun getOneLawyer(@Url url:String): Call<Lawyer>

     @Headers("Content-Type: application/json")
     @POST("api/v1/consults")
     fun createdCase(@Body caso: CasoItem): Call<CasoItem>
}