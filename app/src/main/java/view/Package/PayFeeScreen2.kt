package view.Package

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import model.TransactionHistory
import view.Package.ui.theme.SchoolPayTheme

class PayFeeScreen2 : ComponentActivity() {
    // variable to hold the list of transactions
    private lateinit var transctionHistory:List<TransactionHistory>
    lateinit var  transHistory:List<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    transHistory = listOf(1,2,3,4,5,6,7,8,9,0)
                   payFeeScreen2()
                }
            }
        }
    }


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun payFeeScreen2() {
    var drawerStatte = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val obj = LocalContext.current
    //creating instance of publicClass
    val obj1:PublicClass = PublicClass()
    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    startActivity(Intent(obj, HomeScreen::class.java))},
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
                Button(onClick = { /*TODO*/ },
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
            drawerContent ={
                Text(text = "Select payment channel",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp))
                LazyColumn(verticalArrangement = Arrangement.SpaceEvenly) {
                    item() {
                        Card(modifier = Modifier
                            .fillMaxWidth().height(70.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                startActivity(Intent(obj, PayFeeSchoolCode::class.java))
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mpesa),
                                    contentDescription = "Mpesa"
                                )
                                Text(text = "Safaricom, Mpesa")
                            }
                        }

                    }
                    item() {
                        Card(modifier = Modifier
                            .fillMaxWidth().height(70.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                startActivity(Intent(obj, PayFeeSchoolCode::class.java))
                            }) {
                            Row(
                                modifier = Modifier.fillMaxWidth().align(Alignment.Start),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.airtel),
                                    contentDescription = "Airtel Money"
                                )
                                Text(text = "Airtel, Airtel Money")
                            }
                        }

                    }
                    item() {
                        Card(modifier = Modifier
                            .fillMaxWidth().height(70.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                startActivity(Intent(obj, PayFeeSchoolCode::class.java))
                            }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.telkom),
                                    contentDescription = "T-Kash"
                                )
                                Text(text = "Telkom, T-kash")
                            }
                        }

                    }
                    item() {
                        Card(modifier = Modifier
                            .fillMaxWidth().height(70.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                startActivity(Intent(obj, PayFeeSchoolCode::class.java))
                            }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.bank_icon),
                                    contentDescription = "bank"
                                )
                                Text(text = "Bank")
                            }
                        }

                    }
                }

            },
            drawerState = drawerStatte,
            gesturesEnabled = false,
        //drawerShape = RoundedCornerShape(10.dp),
        drawerElevation = 30.dp) {

            Card(elevation = 5.dp,
                backgroundColor = Color.White,
                modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    topBar = {
                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp, bottom = 16.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Card(
                                    elevation = 4.dp,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(150.dp),
                                    shape = RoundedCornerShape(15.dp)
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment =
                                        Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Ksh.10,000",
                                            Modifier
                                                .background(Color.LightGray)
                                                .width(100.dp)
                                                .height(50.dp),
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Text(text = "Term Fees")
                                    }
                                }

                                Card(
                                    elevation = 4.dp,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(150.dp),
                                    shape = RoundedCornerShape(15.dp)
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment =
                                        Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Ksh.3,000",
                                            Modifier
                                                .background(Color.LightGray)
                                                .width(100.dp)
                                                .height(50.dp),
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Text(text = "Fee Balance")
                                    }
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {

                                Column() {

                                    Button(
                                        onClick ={
                                            scope.launch {
                                                drawerStatte.open()
                                            }
                                        },
                                        shape = CircleShape,
                                        modifier = Modifier.size(70.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Green,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Image(painter =
                                        painterResource(id = R.drawable.pay),
                                            contentDescription ="Pay fee" ,
                                        modifier = Modifier.size(50.dp))
                                    }
                                    Text(text = "Pay Fees")
                                }

                                Column() {

                                    Button(
                                        onClick = { /*TODO*/ },
                                        shape = CircleShape,
                                        modifier = Modifier.size(70.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Red,
                                            contentColor = Color.White
                                        )
                                    ) {
                                    }
                                    Text(text = "Get Loans")
                                }

                                Column() {

                                    Button(
                                        onClick = { /*TODO*/ },
                                        shape = CircleShape,
                                        modifier = Modifier.size(70.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Blue,
                                            contentColor = Color.White
                                        )
                                    ){
                                        Image(painter =
                                        painterResource(id = R.drawable.reports),
                                            contentDescription ="Reports" ,
                                            modifier = Modifier.size(50.dp))
                                    }
                                    Text(text = "Reports")
                                }
                            }
                            Text(text = "Transaction History",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
                        }
                    }
                )
                {
                    if (transHistory.isEmpty()){
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(imageVector = Icons.Filled.History,
                                contentDescription = "Transaction history")
                            Text(text = "There is nothing here",
                                color = Color.Black,
                                fontSize = 16.sp)
                            Text(text = "You have no transactions at the moment")

                        }
                    }
                    else{
                        //null

                        LazyColumn(){
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
                                        // icon
                                        Icon(Icons.Filled.FoodBank,
                                        contentDescription = "Bank Payment")
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
    /*

    */
    }
    
}

/*
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SchoolPayTheme {
        payFeeScreen2()
    }
}

 */