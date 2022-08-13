package view.Package

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import model.Dependants
import view.Package.ui.theme.SchoolPayTheme

class HomeScreen : ComponentActivity() {
    private lateinit var numberOfDependants: List<Dependants>
     lateinit var dependants:List<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    dependants = listOf(1,2,3)
                    payFeeScreen1()
                }
            }
        }
    }

    @Composable
    fun payFeeScreen1() {
        //Function Local variables
        val obj = LocalContext.current
        Scaffold (
            topBar = {
                Row(horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                    Text(text = "Spay",
                    color = Color.White,
                    fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(170.dp))
                    Button(onClick = { startActivity(Intent(obj,NotificationsActivity::class.java)) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(30, 144, 255),
                            contentColor = Color.White
                        )) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "check Notifications"
                        )
                    }
                    Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(30, 144, 255),
                        contentColor = Color.White
                    )) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            },
            backgroundColor = Color(30, 144, 255),
            bottomBar = {
                BottomAppBar(backgroundColor = Color.White,
                    modifier = Modifier.height(150.dp),
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.White
                            )
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(elevation = 5.dp,
                            backgroundColor = Color.LightGray,
                            modifier = Modifier
                                .clickable { /*TODO*/ }
                                .width(100.dp)
                                .height(100.dp),
                            shape = RoundedCornerShape(10.dp)) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Image(painter = painterResource(id = R.drawable.school),
                                    contentDescription = "Registered schools")
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "Schools")
                            }
                        }
                        Card(elevation = 5.dp,
                            backgroundColor = Color.LightGray,
                            modifier = Modifier
                                .clickable { /*TODO*/ }
                                .height(100.dp)
                                .width(100.dp),
                            shape = RoundedCornerShape(10.dp)) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Image(painter = painterResource(id = R.drawable.channels),
                                    contentDescription = "channels")
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "Channels")
                            }
                        }
                        Card(elevation = 5.dp,
                            backgroundColor = Color.LightGray,
                            modifier = Modifier
                                .clickable { /*TODO*/ }
                                .height(100.dp)
                                .width(100.dp),
                            shape = RoundedCornerShape(10.dp)) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Image(painter = painterResource(id = R.drawable.faq),
                                    contentDescription = "FAGs")
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "Ask Abouts")
                            }
                        }
                    }
                })

            },
        content = {

            Card(
                elevation = 5.dp,
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                Scaffold(
                    topBar = {
                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp, top = 16.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                Text(
                                    text = "Dependents",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(70.dp))

                                Text(text = "Add more",
                                    color = Color(30, 144, 255),
                                    modifier = Modifier.clickable {
                                        startActivity(Intent(obj,AddDependantActivity::class.java))
                                    })
                            }
                            Text(text = "Pay For ? Choose dependant to pay for")
                        }
                    }
                ) {
                    LazyColumn(content = {
                        for (i in dependants) {
                            item{
                                Card(elevation = 5.dp,
                                    backgroundColor = Color.LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .height(100.dp)
                                        .clickable {
                                            startActivity(Intent(obj, PayFeeScreen2::class.java))
                                        },
                                    shape = RoundedCornerShape(20.dp)) {
                                    Row(verticalAlignment =
                                    Alignment.CenterVertically,
                                        modifier = Modifier.padding(start = 8.dp)) {
                                        Icon(imageVector = Icons.Filled.Person,
                                            contentDescription = "dependant",
                                            modifier = Modifier.size(50.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(modifier = Modifier.fillMaxHeight(),
                                            verticalArrangement = Arrangement.SpaceEvenly)
                                        {
                                            Text(text = "Dependant Name",
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp)
                                            Text(text = "dependant School" +
                                                    " " + "|"+" " +"Dependant class")
                                            Text(text = "dependant ID")
                                        }
                                    }
                                }
                                //Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    })
                }

            }
        })

    }
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolPayTheme {
        payFeeScreen1()
    }
}

 */