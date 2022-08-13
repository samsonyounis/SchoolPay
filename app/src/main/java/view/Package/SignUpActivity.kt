package view.Package

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import model.SignUpRequest
import model.SignUpResponse
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import view.Package.ui.theme.SchoolPayTheme
import viewModel.Packag.SignInActivityViewModel
import viewModel.Packag.SignInActivityViewModelFactory

class SignUpActivity : ComponentActivity() {
    // Instance of repository
    val repository by lazy { Repository() }

    // Instance of viewmodel Factory
    val viewModelFactory by lazy { SignInActivityViewModelFactory(repository) }

    // instance of view Model
    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(
            SignInActivityViewModel::class.java
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Calling the signUpScreen layout function
                    signUpScreenLayout()
                }
            }
        }
    }
    private var errorMessage = mutableStateOf("")
    //Function to Sign Up users
fun adduserAccount(user:SignUpRequest) {
viewModel.addUserAccount(user)
viewModel.signupResponse.observe(this){response ->
    if(response == "Successfully Registered"){
        //Toast a message
        Toast.makeText(
            applicationContext, response,
            Toast.LENGTH_LONG
        ).show()
        // set a shared preerence storage to save the user
        // then move to OTP Verification Screen
        Log.d("response", response)
        startActivity(
            Intent(
                this@SignUpActivity,
                VerifySignUp::class.java
            )
        )
    }
    else if (response == "Email Already Exists"){
            /*show TextView a containing the following message
            Contains the message " User Already exist"*/
            // or simply Show this Toast
        Log.d("response", response)
            Toast.makeText(applicationContext, response, Toast.LENGTH_LONG).show()
    }
    else{
        Log.d("response", response)
        startActivity(Intent(
            this@SignUpActivity,
            ErrorCodeActivity::class.java).apply
        {
            putExtra(EXTRA_MESSAGE, response.toString())
        })
    }
}
}

    @Composable
    fun signUpScreenLayout() {
        //Function Local variables
        var firstName by remember { mutableStateOf("") }
        var firstNameIsError by remember { mutableStateOf(false) }

        var lastName by remember { mutableStateOf("") }
        var lastNameIsError by remember { mutableStateOf(false) }

        var userName by remember { mutableStateOf("")}
        var userNameIsError by remember { mutableStateOf(false) }

        var emailAddress by remember { mutableStateOf("") }
        var emailIsError by remember { mutableStateOf(false) }

        var ID by remember { mutableStateOf("") }
        var IDisError by remember { mutableStateOf(false) }

        var phoneNumber by remember { mutableStateOf("") }
        var phoneIsError by remember { mutableStateOf(false) }

        var password by remember { mutableStateOf("") }
        var passwordIsError by remember { mutableStateOf(false) }

        var confirmPassword by remember { mutableStateOf("") }
        var confirmPasswordIsError by remember { mutableStateOf(false) }

        var checkedState by remember { mutableStateOf(false) }
        val obj = LocalContext.current
        // Scaffold Scope
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription ="go back" )
                }
            }
        ) {
            //Column Scope
            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Create Parent Account",
                style = MaterialTheme.typography.h1)
             TextField(value = firstName, onValueChange = { firstName = it },
                 keyboardOptions = KeyboardOptions(
                     keyboardType = KeyboardType.Text,
                     imeAction = ImeAction.Next
                 ),
                 label = { Text("First Name") },
                 isError = firstNameIsError,
                 placeholder = { Text(text = "Enter first Name") },
                 keyboardActions = KeyboardActions(
                     onDone = { }
                 ),
                 colors = TextFieldDefaults.textFieldColors(
                     backgroundColor = Color.Unspecified
                 )
             )
             TextField(
                 value = lastName, onValueChange = { lastName = it },
                 label = { Text("last Name") },
                 isError = lastNameIsError,
                 keyboardOptions = KeyboardOptions(
                     keyboardType = KeyboardType.Text,
                     imeAction = ImeAction.Done
                 ),
                 keyboardActions = KeyboardActions(
                     onDone = { }
                 ),
                 placeholder = { Text(text = " Last Name") },
                 colors = TextFieldDefaults.textFieldColors(
                     backgroundColor = Color.Unspecified
                 )
             )
            TextField(
                value = userName, onValueChange = { userName = it },
                label = { Text("User Name") },
                isError = userNameIsError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                placeholder = { Text(text = " User Name") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified
                )
            )
            TextField(
                value = emailAddress, onValueChange = { emailAddress = it },
                label = { Text("Email") },
                isError = emailIsError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                placeholder = { Text(text = " Email Address") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified
                )
            )
            TextField(
                value = ID, onValueChange = { ID = it },
                label = { Text("ID No") },
                isError = IDisError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                placeholder = { Text(text = "ID Number") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified
                )
            )
            TextField(
                value = phoneNumber, onValueChange = { phoneNumber = it },
                label = { Text("Phone No") },
                isError = phoneIsError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                placeholder = { Text(text = "Your Phone number") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified
                )
            )
            TextField(
                value = password, onValueChange = { password = it },
                label = { Text("password") },
                isError = passwordIsError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                placeholder = { Text(text = "Enter Your Password") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified
                )
            )
            TextField(
                value = confirmPassword, onValueChange = { confirmPassword = it },
                label = { Text("Confirm password") },
                isError = confirmPasswordIsError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                placeholder = { Text(text = " Confirm Your Password") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified
                )
            )
            Text(text = errorMessage.value,
            color = Color.Red)
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                onClick = {
                    //Validating the user inputs
                    if (firstName.isBlank()) {
                        firstNameIsError = true
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = false
                        IDisError = false
                        errorMessage.value = "* First Name is Empty"
                    }
                    else if (lastName.isBlank()){
                        lastNameIsError = true
                        firstNameIsError = false
                        phoneIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = false
                        IDisError = false
                        errorMessage.value = "* Last Name is Empty"
                    }
                    else if (phoneNumber.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = true
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = false
                        IDisError = false
                        errorMessage.value = "* Contact field is Empty"
                    }
                    else if (emailAddress.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = true
                        passwordIsError = false
                        confirmPasswordIsError = false
                        IDisError = false
                        errorMessage.value = "*Email Required"
                    }
                    else if (!emailAddress.contains("@")){
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = true
                        passwordIsError = false
                        confirmPasswordIsError = false
                        IDisError = false
                        errorMessage.value = "Incorrect email format"
                    }
                    else if (password.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = false
                        passwordIsError = true
                        confirmPasswordIsError = false
                        IDisError = false
                        errorMessage.value = "* Password field id black"
                    }
                    else if (password.length <6) {
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = false
                        passwordIsError = true
                        confirmPasswordIsError = false
                        IDisError = false
                        errorMessage.value = "* Password too Short"
                    }
                    else if (confirmPassword.isBlank()){
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = true
                        IDisError = false
                        errorMessage.value = "* Please Confirm Your password"
                    }
                    else if (!confirmPassword.contentEquals(password)){
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = true
                        IDisError = false
                        errorMessage.value = "* Passwords not matching"
                    }
                    else if (ID.isBlank()){
                        IDisError = true
                        firstNameIsError = false
                        lastNameIsError = false
                        phoneIsError = false
                        emailIsError = false
                        passwordIsError = false
                        confirmPasswordIsError = false
                        errorMessage.value = "* ID is required"
                    }
                    else {
                        //Calling the sign up API from the backend
                        val user = SignUpRequest(firstName,lastName,userName,emailAddress,
                        ID,phoneNumber,password)
                        adduserAccount(user)
                        errorMessage.value = ""
                    }
                    },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(30, 144, 255),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "save",
                    fontSize = 18.sp
                )
            }
        }
        }
    }
}
