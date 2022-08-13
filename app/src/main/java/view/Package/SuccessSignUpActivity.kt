package view.Package

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import view.Package.ui.theme.SchoolPayTheme

class SuccessSignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    signUpSuccessLayout()
                }
            }
        }
    }
}

@Composable
fun signUpSuccessLayout() {
    //function local variables
    val obj = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly) {
        Text(text = "You're All Set",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black)
        Image(
            painterResource(id = R.drawable.brand2) ,
            contentDescription = "Icon Brand",
        modifier = Modifier.width(200.dp).height(300.dp))
        Text(text = "Your Account has been created successfully")

        Row() {

            Text(text = "Back to")
            Text(text = "Sign in",
                Modifier.clickable {
                    obj.startActivity(Intent(obj,SignInActivity::class.java))
                },
                color = Color(30,144,255)
            )
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolPayTheme {
        Greeting("Android")
    }
}

 */