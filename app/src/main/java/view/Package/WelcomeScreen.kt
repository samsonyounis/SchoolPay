package view.Package

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun welcomeScreen(navController: NavController) {

    //Column Layout
    Column(modifier= Modifier.fillMaxSize().background(Color(30,144,255)),
        horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly) {
        Image(
            painterResource(id = R.drawable.animation) ,
            contentDescription = "Icon Brand",
            modifier = Modifier.size(300.dp))

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
                // navigating to login screen.
                Button(onClick = {
                      navController.navigate("loginScreen")
                },
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