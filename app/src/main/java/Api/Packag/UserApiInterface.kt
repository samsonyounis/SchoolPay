package Api.Packag

import model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiInterface {

    @POST("register")
    fun addUserAccount(@Body user:SignUpRequest):Call<SignUpResponse>

    @POST ("login")
     fun loginRequest(@Body loginRequest: LoginRequest):Call<LoginResponse>

     @GET("dependants")
     fun getDependants():Call<List<Dependants>>

    @GET("dependants")
    fun getTransactionHistory():Call<List<TransactionHistory>>

    // function to send back otp code to the server.
    @POST("codejava/otp")
    fun sendBackOtp(@Body otp:String):Call<String>
}