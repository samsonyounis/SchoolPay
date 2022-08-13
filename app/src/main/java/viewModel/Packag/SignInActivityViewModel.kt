package viewModel.Packag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.SignUpRequest
import model.LoginRequest
import model.LoginResponse
import model.SignUpResponse
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivityViewModel(private val repository: Repository):ViewModel() {
    // Creating Mutable LiveData Variables here
var signupResponse:MutableLiveData<String> = MutableLiveData()
    //function to add user account
    fun addUserAccount(user: SignUpRequest){
        // Accessing the addUserAccount function from the repository
        repository.addUserAccount(user).enqueue(object :
            Callback<SignUpResponse> {
            // this method will be executed if you have recieved Http response
            // Your status code will decide if the Http response is success or error
            override fun onResponse(
                call: Call<SignUpResponse>, response: Response<SignUpResponse>
            ) {
                //here your reponse  code is in the range of 200 to 299
                // more else block can be used here to determine different actions
                // for different codes in this range(200 - 299)
                if (response.isSuccessful) {
                    if (response.body()!!.message == "Successfully Registered") {
                        // setting signUpResponse variable to success string
                        signupResponse.value = response.body()!!.message
                    }
                    // here user login credentials provided are incorrect
                    else {
                        // setting loginResponse variable to this message
                        signupResponse.value = "Email Already Exists"
                    }
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    // setting loginResponse variable to error code
                    signupResponse.value = response.code().toString()

                }
            }
            // Invoked when a network exception/error occurred or
            // establishing connection with the server.
            // unexpected exception occurred creating the request or processing the response.
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                // setting loginResponse variable to the error message in the t object
                signupResponse.value = t.message
            }
        })
    }


    fun loginRequest(loginRequest: LoginRequest):Call<LoginResponse>{
            return repository.loginRequest(loginRequest)
    }
}