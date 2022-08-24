package view.Package

import android.content.Intent
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import model.SignUpRequest
import view.Package.ReusableFunctions.backArrow
import view.Package.ReusableFunctions.commonButton
import view.Package.ReusableFunctions.textField
import viewModel.Packag.SignUpScreenViewModel

@Composable
fun signUpScreen(navController: NavController, viewModel: SignUpScreenViewModel) {
    //Function Local variables
    var firstName by remember { mutableStateOf("") }
    var firstNameIsError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf("") }
    var lastNameIsError by remember { mutableStateOf(false) }
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
    var showProgress by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val obj = LocalContext.current
    // Creating instance of event lifecyle owner
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
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
    // Scaffold Scope
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            backArrow(navController = navController)
        }
    ) {
        // Show the dialog box only when pay Now button is clicked
        if (openDialog== true) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(text = "Creating User Account",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)
                },
                text = {
                    if (showProgress == true){
                        CircularProgressIndicator(
                            color = Color(30, 144, 255),
                            strokeWidth = ProgressIndicatorDefaults.StrokeWidth)
                    }
                    else{
                        showProgress==false
                    }
                },
                buttons = { }
            )
        }
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
            //implementing the text field to enter the user first name
            textField(valueText = firstName, onValueChange = {firstName = it},
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next,
                label = "first name", placeholder ="enter your first name" , isError = firstNameIsError
            )
            //implementing the text field to enter the user last name
            textField(valueText = lastName, onValueChange = {lastName = it},
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next,
                label = "last name", placeholder ="enter your last name" , isError = lastNameIsError
            )
            //implementing the text field to enter the user email address
            textField(valueText = emailAddress, onValueChange = {emailAddress = it},
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next,
                label = "email address", placeholder ="enter your email address" , isError = emailIsError
            )
            //implementing the text field to enter the user ID
            textField(valueText = ID, onValueChange = {ID = it},
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next,
                label = "ID number", placeholder ="enter your ID number" , isError = IDisError
            )
            //implementing the text field to enter the user phone number
            textField(valueText = phoneNumber, onValueChange = {phoneNumber = it},
                keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next,
                label = "phone number", placeholder ="enter your phone number" , isError = phoneIsError
            )
            //implementing the text field to enter the password
            textField(valueText = password, onValueChange = {password = it},
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Next,
                label = "password", placeholder ="enter your password" , isError = passwordIsError
            )
            //implementing the text field to confirm password
            textField(valueText = confirmPassword, onValueChange = {confirmPassword = it},
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done,
                label = "confirm password", placeholder ="confirm your pssword" , isError = confirmPasswordIsError
            )
            // validation error message here
            Text(text = errorMessage,
                color = Color.Red)
            // sign up buuton here
            commonButton(text = "save", navController = navController) {
                //Validating the sign up credentials.
                if (firstName.isBlank()) {
                    firstNameIsError = true
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = false
                    passwordIsError = false
                    confirmPasswordIsError = false
                    IDisError = false
                    errorMessage = "* First Name is Empty"
                }
                else if (lastName.isBlank()){
                    lastNameIsError = true
                    firstNameIsError = false
                    phoneIsError = false
                    emailIsError = false
                    passwordIsError = false
                    confirmPasswordIsError = false
                    IDisError = false
                    errorMessage = "* Last Name is Empty"
                }
                else if (phoneNumber.isBlank()){
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = true
                    emailIsError = false
                    passwordIsError = false
                    confirmPasswordIsError = false
                    IDisError = false
                    errorMessage = "* Contact field is Empty"
                }
                else if (emailAddress.isBlank()){
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = true
                    passwordIsError = false
                    confirmPasswordIsError = false
                    IDisError = false
                    errorMessage = "*Email Required"
                }
                else if (!emailAddress.contains("@")){
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = true
                    passwordIsError = false
                    confirmPasswordIsError = false
                    IDisError = false
                    errorMessage = "Incorrect email format"
                }
                else if (password.isBlank()){
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = false
                    passwordIsError = true
                    confirmPasswordIsError = false
                    IDisError = false
                    errorMessage = "* Password field id black"
                }
                else if (password.length <6) {
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = false
                    passwordIsError = true
                    confirmPasswordIsError = false
                    IDisError = false
                    errorMessage = "* Password too Short"
                }
                else if (confirmPassword.isBlank()){
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = false
                    passwordIsError = false
                    confirmPasswordIsError = true
                    IDisError = false
                    errorMessage = "* Please Confirm Your password"
                }
                else if (!confirmPassword.contentEquals(password)){
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = false
                    passwordIsError = false
                    confirmPasswordIsError = true
                    IDisError = false
                    errorMessage = "* Passwords not matching"
                }
                else if (ID.isBlank()){
                    IDisError = true
                    firstNameIsError = false
                    lastNameIsError = false
                    phoneIsError = false
                    emailIsError = false
                    passwordIsError = false
                    confirmPasswordIsError = false
                    errorMessage = "* ID is required"
                }
                else {
                    openDialog = true
                    showProgress = true
                    errorMessage = ""
                    //create user object
                    val user = SignUpRequest(firstName,lastName,emailAddress,
                        ID,phoneNumber,password)
                    // accessing the addUserAccount function from the viewmodel
                    viewModel.addUserAccount(user)
                    // then observing the signup response from the view model
                    viewModel.signUpResponse.observe(lifeCycleOwner) { response ->
                        if (response == "Successfully Registered") {
                            openDialog = false
                            showProgress = false
                            errorMessage = ""
                            //Toast a message
                            Toast.makeText(obj.applicationContext, response, Toast.LENGTH_LONG).show()
                            // Move the user to the OTP screen
                            obj.startActivity(Intent(obj, SignUpOtpScreen::class.java))
                            navController.popBackStack()
                        } else if (response == "Email Already Exists") {
                            openDialog = false
                            showProgress = false
                            errorMessage = response
                        } else {
                            //toast the error code or error message
                            Toast.makeText(obj.applicationContext, response, Toast.LENGTH_LONG).show()
                            // Move to the next screen and display the error code
                            navController.popBackStack()
                            navController.navigate("errorScreen/Error Code!!\n\n $response")
                            openDialog = false
                            showProgress = false
                            errorMessage = ""
                        }
                    }
                }
            }
        }
    }
}