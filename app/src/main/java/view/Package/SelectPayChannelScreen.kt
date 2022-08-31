package view.Package

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import view.Package.ReusableFunctions.CardForFeeBalance
import view.Package.ReusableFunctions.customCardChannel
import view.Package.ReusableFunctions.roundButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun selectChannelScreen(navController: NavController) {
    var drawerStatte = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var transHistory = listOf(1,2,3,4,5,6,7,8,9,0)
    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    // navigatingback stack
                  navController.navigateUp()
                },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(30,144,255),
                        contentColor = Color.White
                    )) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "go back")
                }
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "dependant Name.\n" +
                            "School Name"+"|"+"Class\n" +
                            "ID Number")
                }
                Button(onClick = {
                                 // navigating to notifications screen
                },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(30,144,255),
                        contentColor = Color.White
                    )) {
                    Icon(imageVector = Icons.Filled.Notifications,
                        contentDescription = "go to notifications")
                }
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(30,144,255),
                        contentColor = Color.White
                    )) {
                    Icon(imageVector = Icons.Filled.Settings,
                        contentDescription = "go to settings")
                }
            }
        },
        backgroundColor = Color(30,144,255),
    ) {
        BottomDrawer(
            drawerContent = {
                Text(
                    text = "Select payment channel",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(verticalArrangement = Arrangement.SpaceEvenly) {
                    item (){
                        customCardChannel(navController,imageId = R.drawable.mpesa ,
                            imageDes = "mpesa", text = "Safaricom mpesa")
                    }
                    item() {
                        customCardChannel(imageId = R.drawable.airtel,imageDes = "airtel",
                            text = "Airtel, Airtel Money", navController = navController)
                    }
                    item() {
                        customCardChannel(navController,imageId = R.drawable.telkom ,
                            imageDes = "telkom", text = "Telkom, T-kash")
                    }
                    item() {
                        customCardChannel(navController,imageId = R.drawable.bank_icon,
                            imageDes = "bank", text = "Bank")
                    }
                }

            },
            drawerState = drawerStatte,
            gesturesEnabled = false,
            drawerElevation = 30.dp
        ) {

            Card(
                elevation = 5.dp,
                backgroundColor = Color.White,
                modifier = Modifier.fillMaxSize()
            ) {
                Scaffold(
                    topBar = {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp, bottom = 16.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                CardForFeeBalance(amountFigures = "Ks.10,000", amountDes = "Term Fees")
                                CardForFeeBalance(amountFigures = "Ks.5000", amountDes = "Fee Balance")
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                roundButton(
                                    onClick = {scope.launch { drawerStatte.open() } },
                                    imageId = R.drawable.pay, imageDes ="pay fee" ,
                                    text ="Pay Fees", Color.Green
                                )
                                roundButton(
                                    onClick = {/*TODO*/ },
                                    imageId = R.drawable.school,imageDes ="loans",
                                    text ="Get loans", Color.Red
                                )
                                roundButton(
                                    onClick = {/*TODO*/ },
                                    imageId = R.drawable.reports,imageDes ="reports",
                                    text ="Reports",Color.Blue
                                )
                            }
                            Text(
                                text = "Transaction History",
                                style = MaterialTheme.typography.h1,
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                        }
                    }
                )
                {
                    if (transHistory.isEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.History,
                                contentDescription = "Transaction history"
                            )
                            Text(
                                text = "There is nothing here",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                            Text(text = "You have no transactions at the moment")

                        }
                    } else {
                        //null

                        LazyColumn() {
                            for (i in transHistory) {
                                item {
                                    Row(
                                        modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 16.dp),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        //Check the trans method to place the correct
                                        Icon(
                                            Icons.Filled.FoodBank,
                                            contentDescription = "Bank Payment"
                                        )
                                        Column() {
                                            Text(text = "Bank Name")
                                            Text(text = "Acount Number")
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column() {
                                            Text(text = "Amount in Ksh.")
                                            Text(text = "TimeStamp")
                                        }
                                    }
                                    Divider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}