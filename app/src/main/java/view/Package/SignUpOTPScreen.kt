package view.Package

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import view.Package.ui.theme.SchoolPayTheme

class VerifySignUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Calling the composable function here
                    signUpOTPScreen()
                }
            }
        }
    }
}

@Composable
fun signUpOTPScreen() {
    //function local variables
    var verificationCode by remember { mutableStateOf("") }
    val obj = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(16.dp)) {
        Text(text = "Verify Phone Number",
            style = MaterialTheme.typography.h1)

        Text(text = "+254701*****38")
        OutlinedTextField(value =verificationCode ,
            onValueChange ={verificationCode=it },
            modifier = Modifier.focusable(true),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Default)
        )
        Row(horizontalArrangement = Arrangement.End) {
            Text(text = "Resend Code")
        }
        Button(onClick = {
            // send back the OTP code to the server
            // get the server response
            // move to the successful sign up screen if the response is ok
            obj.startActivity(Intent(obj, SuccessSignUpActivity::class.java)
        )
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(30, 144, 255),
                contentColor = Color.White),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ){
            Text(text = "Verify")
        }

    }
}