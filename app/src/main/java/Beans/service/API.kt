package Beans.service

import Beans.service.`class`.*
import retrofit2.Call
import retrofit2.http.*

interface API {
     @GET
     fun getCases(@Url url:String): Call<List<CasesResponse>>
     @GET
     fun getSubscriptions(@Url url:String): Call<List<SubscriptionResponse>>
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

     @GET
     fun getUserData(@Url url:String): Call<Client>

     @Headers("Content-Type: application/json")
     @PUT
     fun updateUserData(@Url url:String, @Body client: Client): Call<Client>

     @Headers("Content-Type: application/json")
     @POST("api/v1/consults")
     fun createdCase(@Body caso: CasoItem): Call<CasoItem>
}