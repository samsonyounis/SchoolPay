package repository

import Api.Packag.retrofitObjectInstance
import model.SignUpRequest
import model.LoginRequest
import model.LoginResponse
import model.SignUpResponse
import retrofit2.Call

class Repository {

     fun addUserAccount(account: SignUpRequest):Call<SignUpResponse>{
       return retrofitObjectInstance.ApiConnect.addUserAccount(account)
    }

    fun loginRequest(loginRequest: LoginRequest):Call<LoginResponse>{
        return retrofitObjectInstance.ApiConnect.loginRequest(loginRequest)
    }

    fun  sendBackOtp(otp:String):Call<String>{
        return retrofitObjectInstance.ApiConnect.sendBackOtp(otp)
    }
}