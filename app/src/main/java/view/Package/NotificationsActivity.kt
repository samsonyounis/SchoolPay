package view.Package

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Notifications
import view.Package.ui.theme.SchoolPayTheme

class NotificationsActivity : ComponentActivity() {
    // private class varible to hold the list of notifications
    private lateinit var listOfNotifications:List<Notifications>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    notificationsScreen()
                }
            }
        }
    }
}

@Composable
fun notificationsScreen() {
// function variables
    var numberOfNotifications = listOf<Int>()
    if (numberOfNotifications.isEmpty()){

        Scaffold(
            topBar = {
                Column(verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(top = 16.dp)) {
                    Divider()
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.ArrowBack ,
                                contentDescription ="go back")
                        }

                        Text(text = "Notifications",
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold)
                        Icon(imageVector = Icons.Filled.Menu,
                            contentDescription = "menu")
                    }
                    Divider()
                }

            }
        ) {
           Column(modifier = Modifier.fillMaxSize().padding(10.dp),
           verticalArrangement = Arrangement.SpaceEvenly,
           horizontalAlignment = Alignment.CenterHorizontally) {
               Icon(imageVector = Icons.Filled.Notifications,
                   contentDescription = "Notifications Icon",
               modifier = Modifier.size(200.dp))
               Text(text = "There is Nothing here [!!]",
               color = Color.Black,
               fontSize = 18.sp,
               fontWeight = FontWeight.Bold)

               Text(text = "There is No notifications posted yet\n" +
                       "you will get notifications here when it is posted\n" +
                       "or check notifications settings t0 allow notifications from this app",
                   //color = Color.Black,
                   fontSize = 16.sp)
                  //fontWeight = FontWeight.Bold)

               Button(onClick = { /*TODO*/ },
                   colors = ButtonDefaults.buttonColors(
                       backgroundColor = Color(30,144,255),
                       contentColor = Color.White
                   ),
                   shape = RoundedCornerShape(20.dp)) {
                   Text(text = "Notifications Settings",
                       fontSize = 20.sp)
               }
           }
        }
    }


}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolPayTheme {
        Greeting2("Android")
    }
}
 */