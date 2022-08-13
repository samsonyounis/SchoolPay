package view.Package

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import view.Package.ui.theme.SchoolPayTheme

class PayFeeEnterAmount : ComponentActivity() {
    private var accountNo:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    accountNo = intent.getStringExtra(EXTRA_MESSAGE).toString()
                    payFeeEnterAmountScreen()

                }
            }
        }
    }


    @Composable
    fun payFeeEnterAmountScreen() {
        //Function Local Variables
        // variable to hold the Mobile number or the bank number from which deduction is
        // to be made
        var amount by remember { mutableStateOf("") }
        var enableButton by remember { mutableStateOf(false) }
        var buttonBgColor by remember { mutableStateOf(Color.LightGray) }
        var buttonContentColor by remember { mutableStateOf(Color.Black) }
        val obj = LocalContext.current
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.Start)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "go Back"
                        )
                    }
                    Column {
                        Text(text = "GJ",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .background(
                                shape = CircleShape,
                                color = Color.LightGray
                            )
                            .size(70.dp).align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center)
                        Text(
                            text = "Account Name",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "phone No:"+accountNo.substring(0,4)+"****"+
                            accountNo.substring(8),
                            //color = Color.Black,
                            fontSize = 16.sp,
                            //fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(value = amount,
                    onValueChange = { amount = it },
                    //isError = emailIsError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Default
                    ),
                    label = { Text("Enter Amount") },
                    placeholder = { Text(text = "Enter Amount to pay") },
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    ),
                    // trailingIcon = {
                    // Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Address")
                    // },
                    //modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                )
                if (amount.length >= 1) {
                    enableButton = true
                    buttonBgColor = Color(30, 144, 255)
                    buttonContentColor = Color.White
                }
                else{
                    enableButton = false
                    buttonBgColor = Color.LightGray
                    buttonContentColor = Color.Black
                }

                Button(
                    onClick = {
                              startActivity(Intent(obj,PayFeeOTPActivity::class.java))
                    },
                    enabled = enableButton,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonBgColor,
                        contentColor = buttonContentColor
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text(
                        text = "Continue",
                        modifier = Modifier.padding(start = 100.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "go forward"
                    )

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