package view.Package

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import model.LoginRequest
import model.LoginResponse
import repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import view.Package.ui.theme.SchoolPayTheme
import viewModel.Packag.SignInActivityViewModel
import viewModel.Packag.SignInActivityViewModelFactory

class SignInActivity : ComponentActivity() {
    //declaring the viewmodel instance
    private lateinit var viewModel: SignInActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    signInScreenLayout()
                }
            }
        }
        val repository = Repository()
        val viewModelFactory = SignInActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).
        get(SignInActivityViewModel::class.java)

    }
    private var errorMessage = mutableStateOf("")

    fun AuthenticateLogin(email: String, passWord: String) {
        //create loginRequest  object using the user name and password
        val loginRequest: LoginRequest = LoginRequest(email,passWord)
        viewModel.loginRequest(loginRequest).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>, response: Response<LoginResponse>
            ) {
                if (response.isSuccessful){
                    if (response.body()!!.message=="success"){
                        //Toast a message
                        Toast.makeText(
                            applicationContext, response.body()!!.message,
                            Toast.LENGTH_LONG).show()
                        // set a shared preerence storage to save the user
                        // then move to Dashboard/Home Screen
                        startActivity(
                            Intent(this@SignInActivity,
                                HomeScreen::class.java)
                        )
                    } else{
                        /*show text Text view above the email textField that
                        Contains the message " Incorrect password or Email"*/
                        // or simply Show this Toast
                        Toast.makeText(
                            applicationContext, response.body()!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                        errorMessage.value = "Incorrect Password for email"
                    }
                } else {
                    //Toast this code.
                    // or move to the next screen and display the error code
                    // in a textView at the center of the screen
                    Toast.makeText(
                        applicationContext, response.code().toString(),
                        Toast.LENGTH_LONG).show()

                    startActivity(Intent(this@SignInActivity,
                        ErrorCodeActivity::class.java).apply
                    {
                        putExtra(EXTRA_MESSAGE,response.code().toString()+"\n " +
                                "resource not found or bad request")
                    })
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // or move to the next sreen and display the error message
                // in a textView at the center of the screen
                // or simply toast the error message
                Toast.makeText(applicationContext,
                    t.message+"\nUnable to connect to server",
                    Toast.LENGTH_LONG).show()
                startActivity(Intent(this@SignInActivity,
                    ErrorCodeActivity::class.java).apply
                {
                    putExtra(EXTRA_MESSAGE,t.message+"\n unable to" +
                            " connect to server")
                })
            }

        })
    }

    @Composable
    fun signInScreenLayout() {
        //Function local Variables
        var emailIsError by remember { mutableStateOf(false) }
        var passwordIsError by remember { mutableStateOf(false) }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var checkedState by remember { mutableStateOf(false) }
        val obj = LocalContext.current
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
            Text(
                text = "Account Sign in",
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(18.dp))
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
                Text(text = "Not a member?")
                Text(text = "Sign Up",
                    Modifier.clickable {
                        obj.startActivity(Intent(obj, SignUpActivity::class.java))
                    },
                    color = Color(30, 144, 255)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(value = email, onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                label = { Text("User Name") },
                isError = emailIsError,
                placeholder = { Text(text = "User Name") },
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "user name /Email Address"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = password, onValueChange = { password = it },
                label = { Text("Password") },
                isError = passwordIsError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Your password"
                    )
                },
                placeholder = { Text(text = " password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                visualTransformation = PasswordVisualTransformation()
            )
            Text(text = errorMessage.value,
            color = Color.Red)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(checked = checkedState, onCheckedChange = { checkedState = it })
                Text(text = "Remember Me",
                modifier = Modifier.align(Alignment.CenterVertically))
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Forgot Password", Modifier.clickable {/*TODO*/ }.
                align(Alignment.CenterVertically),
                    textDecoration = TextDecoration.Underline,
                    color = Color(30, 144, 255)
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
            Button(onClick = { startActivity(Intent(obj, HomeScreen::class.java)) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(30, 144, 255,),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
                Text(text = "pay")
            }
            Button(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                onClick = {
                    // Validate details first
                    if(email.isBlank()) {
                        emailIsError = true
                        passwordIsError = false
                        errorMessage.value = "*Email required"
                    }
                    else if (password.isBlank()){
                        passwordIsError = true
                        emailIsError = false
                        errorMessage.value = "*Password required"
                    }
                    else if (password.length < 6 ){
                        passwordIsError = true
                        emailIsError = false
                        errorMessage.value = "*Password is too short"
                    }
                    else{
                        obj.startActivity(Intent(obj,HomeScreen::class.java))
                        //AuthenticateLogin(email,password)
                        email = ""
                        password= ""
                        emailIsError = false
                        passwordIsError = false
                        errorMessage.value= ""
                    }
                    },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(30, 144, 255),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sign In")
            }

            }
        }
    }
