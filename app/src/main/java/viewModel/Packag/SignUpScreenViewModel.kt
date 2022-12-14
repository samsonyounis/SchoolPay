package viewModel.Packag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.SignUpRequest
import model.SignUpResponse
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpScreenViewModel(private val repository: Repository):ViewModel() {
    // Creating Mutable LiveData Variables here
var signUpResponse:MutableLiveData<String> = MutableLiveData()
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
                        signUpResponse.value = response.body()!!.message
                    }
                    // here user login credentials provided are incorrect
                    else {
                        // setting loginResponse variable to this message
                        signUpResponse.value = "Email Already Exists"
                    }
                }
                //here https status code is in the range of 300s, 400s and 500s
                // apllication level failure.
                // you can use different codes in this ranges to determine and display
                // different messages to the user.
                else {
                    // setting loginResponse variable to error code
                    signUpResponse.value = response.code().toString()

                }
            }
            // Invoked when a network exception/error occurred or
            // establishing connection with the server.
            // unexpected exception occurred creating the request or processing the response.
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                // setting loginResponse variable to the error message in the t object
                signUpResponse.value = t.message
            }
        })
    }
}
class SignUpScreenViewModelFactory(private val repository: Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpScreenViewModel(repository) as T
    }
}