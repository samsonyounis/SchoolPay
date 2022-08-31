package view.Package

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun  homeScreen(navController: NavController){
    //Function Local variables
    var dependants = listOf(1,2,3)
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
                Button(onClick = {
                    // navigting to notifications screen
                    navController.navigate("notificationScreen")},
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
                    Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween) {
                    Divider(
                        color = Color.Black, thickness = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
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
                                Image(
                                    painter = painterResource(id = R.drawable.school),
                                    contentDescription = "Registered schools"
                                )
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
                                Image(
                                    painter = painterResource(id = R.drawable.channels),
                                    contentDescription = "channels"
                                )
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
                                Image(
                                    painter = painterResource(id = R.drawable.faq),
                                    contentDescription = "FAGs"
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "Ask Abouts")
                            }
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
                    .fillMaxHeight()
            ) {
                Scaffold(
                    topBar = {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
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
                                        // navigating to add dependant screen
                                        navController.navigate("addDependantScreen")
                                    })
                            }
                            Text(text = "Pay For ? Choose dependant to pay for")
                        }
                    }
                ) {
                    LazyColumn(content = {
                        for (i in dependants) {
                            item {
                                Card(
                                    elevation = 5.dp,
                                    backgroundColor = Color.LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .height(100.dp)
                                        .clickable {
                                            // navigating to selectChannelScreen
                                            navController.navigate("selectPayChannelScreen")
                                        },
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Row(
                                        verticalAlignment =
                                        Alignment.CenterVertically,
                                        modifier = Modifier.padding(start = 8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Person,
                                            contentDescription = "dependant",
                                            modifier = Modifier.size(50.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(
                                            modifier = Modifier.fillMaxHeight(),
                                            verticalArrangement = Arrangement.SpaceEvenly
                                        )
                                        {
                                            Text(
                                                text = "Dependant Name",
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp
                                            )
                                            Text(text = "dependant School")
                                            Text(text = "dependant Reg_No")
                                        }
                                    }
                                }
                            }
                        }
                    })
                }

            }
        })
}