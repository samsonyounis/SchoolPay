package view.Package

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import view.Package.ui.theme.SchoolPayTheme

class PayFeeConfirmScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    confirmPaymentScreen()
                }
            }
        }
    }

    @Composable
    fun confirmPaymentScreen() {
        //Function Local Variables
        val obj = LocalContext.current
        var otpCode by remember { mutableStateOf("") }
        var mpesaPin by remember { mutableStateOf("") }
        var openDialog by remember { mutableStateOf(false) }
        //Scaffold layout here
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
                }
            }
        ) {
            // Show the dialog box only when pay Now button is clicked
            if (openDialog== true) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    title = {
                        Text(text = "Enter Mpesa pin",
                        color = Color.White)
                    },
                    text = {
                        TextField(value = mpesaPin, onValueChange ={mpesaPin = it},
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Unspecified,
                            cursorColor = Color(30, 144, 255),
                            textColor = Color.White,
                            focusedIndicatorColor = Color(30,144,255),
                            unfocusedIndicatorColor = Color(30,144,255)
                        ))
                    },
                    buttons = {
                        Row(
                            modifier = Modifier
                                .padding(all = 8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = { openDialog = false },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Black
                                )
                            ) {
                                Text("Cancel",
                                color = Color(30, 144, 255))
                            }

                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { /*TODO*/},
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Black
                                )
                            ) {
                                Text("Send",
                                    color = Color(30, 144, 255))
                            }
                        }
                    },
                    backgroundColor = Color.Black
                )
            }

            Card(
                elevation = 5.dp,
                backgroundColor = Color.LightGray,
                modifier = Modifier.padding(32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Icon(
                        imageVector = Icons.Filled.Apartment,
                        contentDescription = "School"
                    )

                    Text(text = "School Code\n Code")
                    Text(text = "School Name\n Name")
                    Text(text = "Student's Reg_No\n Reg_No")
                    Text(text = "Student's Name\n Name")
                    Text(text = "Amount\n Ksh.500")

                    Button(
                        onClick = { openDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(30, 144, 255),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Text(
                            text = "pay Now",
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
