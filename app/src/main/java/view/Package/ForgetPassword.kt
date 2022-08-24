package view.Package

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.Package.ReusableFunctions.backArrow
import view.Package.ReusableFunctions.commonButton

@Composable
fun forgotPasswordScreen(navController: NavController) {
    // Function Local Variables
    var email by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // calling the arrowBackTopRow function
        backArrow(navController = navController)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Forgot Password?", style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "please Enter your email and we will send you" +
                    "\n a password to your account"
        )
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(value = email, onValueChange = { email = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            label = { Text("Email") },
            placeholder = { Text(text = "Enter Your password") },
            keyboardActions = KeyboardActions(
                onDone = {
                    // call the request password function here
                }
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email Address"
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(100.dp))
        // reusing the button function
        commonButton(onClick = {
            // call the request password function
            // then navigate to back to login screen to input the new password
             navController.navigate("loginScreen")
        }, text = "continue", navController = navController)

        Spacer(modifier = Modifier.height(100.dp))
        Row() {
            Text(text = "Don't have an account?")
            Text(text = "Sign Up", Modifier.clickable {
                // navigating to the sign up screen
                navController.navigate("signUpScreen")
            },
                color = Color(30, 144, 255)
            )
        }
    }
}