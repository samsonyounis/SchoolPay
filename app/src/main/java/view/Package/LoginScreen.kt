package view.Package

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import model.LoginRequest
import view.Package.ReusableFunctions.backArrow
import view.Package.ReusableFunctions.circularProgressIndicator
import view.Package.ReusableFunctions.commonButton
import view.Package.ReusableFunctions.commonOutlinedTextField
import viewModel.Packag.LoginScreenViewModel

@Composable
fun loginScreen(navController: NavController, viewModel: LoginScreenViewModel) {
    //Function local Variables
    val obj = LocalContext.current // instance of session Manager
    val sessionManager = SessionManager(obj) // variable to hold the user token
    var userToken:String by
    remember{ mutableStateOf(sessionManager.fetchAuthToken().toString()) }
    // lifeCycle owner
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var emailIsError by remember { mutableStateOf(false) }
    var passwordIsError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var checkedState by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }

    DisposableEffect(lifeCycleOwner){
        val observer = LifecycleEventObserver{source, event ->
            if (event == Lifecycle.Event.ON_RESUME){
                showProgress = false
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        backArrow(navController = navController)
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
                    // navigating to sign up screen
                    navController.navigate("signUpScreen")
                },
                color = Color(30, 144, 255)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        //implementing the outlined text field for entering the email
        commonOutlinedTextField(valueText = email, onValueChange = {email = it}, isError = emailIsError,
            labelText ="email" , placeholderText = "enter your email address",
            leadingIcon = Icons.Filled.Person, iconDescription = "email",
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next,
            visualTransFormation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.height(10.dp))

        //implementing the outlined text field for entering the password
        commonOutlinedTextField(valueText = password, onValueChange = {password = it},
            isError = passwordIsError,
            labelText ="password" , placeholderText = "enter your password",
            leadingIcon = Icons.Filled.Lock, iconDescription = "password",
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done,
            visualTransFormation = PasswordVisualTransformation()
        )
        // validation error message
        Text(text = errorMessage,
            color = Color.Red)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = checkedState, onCheckedChange = { checkedState = it })
            Text(text = "Remember Me",
                modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = "Forgot Password",
                Modifier
                    .clickable {
                        // navigating to the forget password screen
                        navController.navigate("forgetPasswordScreen")
                    }
                    .align(Alignment.CenterVertically),
                textDecoration = TextDecoration.Underline,
                color = Color(30, 144, 255)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        // showing the circular progress while logging in the user.
        circularProgressIndicator(showProgress = showProgress)

        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            // navigating to hom screen.
            navController.navigate("signUpSccessScreen")},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(30, 144, 255,),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "success signup")
        }

        commonButton(text = "Sign in", navController = navController, onClick = {
            // Validate login credentials here
            if(email.isBlank()) {
                emailIsError = true
                passwordIsError = false
                errorMessage = "*Email required"
            }
            else if (password.isBlank()){
                passwordIsError = true
                emailIsError = false
                errorMessage = "*Password required"
            }
            else if (password.length < 6 ){
                passwordIsError = true
                emailIsError = false
                errorMessage = "*Password is too short"
            }
            else{
                showProgress = true
                email = ""
                password= ""
                emailIsError = false
                passwordIsError = false
                errorMessage= ""
                // creating the object
                val loginRequest = LoginRequest(email, password)
                //AuthenticateLogin(email,password)
                viewModel.loginRequest(loginRequest)
                // observing the login response from the view model.
                viewModel.loginResponse.observe(lifeCycleOwner){response ->
                    if (response.toString() == "success"){
                        // then move to home screen
                        navController.popBackStack()
                        navController.navigate("homeScreen")
                    }
                    else if(response.toString() == "invalid"){
                        errorMessage = response
                        showProgress = false
                    }
                    else {
                        navController.popBackStack()
                        navController.navigate("errorScreen/$response")
                    }

                }
            }
        })

    }
}