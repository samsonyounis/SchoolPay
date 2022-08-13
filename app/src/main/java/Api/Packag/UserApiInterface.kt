package Api.Packag

import model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiInterface {

    @POST("parent/signup")
    fun addUserAccount(@Body user:SignUpRequest):Call<SignUpResponse>


    @POST ("loginAuthentication")
     fun loginRequest(@Body loginRequest: LoginRequest):Call<LoginResponse>

     @GET("dependants")
     fun getDependants():Call<List<Dependants>>

    @GET("dependants")
    fun getTransactionHistory():Call<List<TransactionHistory>>
}