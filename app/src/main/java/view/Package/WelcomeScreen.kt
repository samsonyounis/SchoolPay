package view.Package

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun welcomeScreen() {
    // function scope variables
    val obj = LocalContext.current
    //Column Layout
    Column(modifier= Modifier.fillMaxSize().background(Color(30,144,255)),
        horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly) {
        Image(
            painterResource(id = R.drawable.animation) ,
            contentDescription = "Icon Brand",
            modifier = Modifier.size(300.dp))
                //.background(Color(30, 144, 255)))
        Card(backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp,),
            elevation = 5.dp,
            modifier = Modifier
                .height(300.dp)
                .padding(16.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly) {

                Text(text ="Welcome to Spay",
                    style = MaterialTheme.typography.h1)
                Spacer(modifier = Modifier.height((16.dp)))
                Text(text = "Number 1 platform that aims in making\n school fee payment easy, fast and" +
                        " convient")
                Spacer(modifier = Modifier.height(64.dp))
                Button(onClick = {obj.startActivity(Intent(obj,SignInActivity::class.java)) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(30,144,255)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp)

                ) {
                    Text(text = "Get Started",
                        color = Color.White,
                    )
                }
            }
        }
    }
}