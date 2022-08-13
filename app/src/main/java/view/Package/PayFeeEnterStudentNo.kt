package view.Package

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
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
import androidx.compose.material.icons.filled.House
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import view.Package.ui.theme.SchoolPayTheme

class PayFeeEnterStudentNo : ComponentActivity() {
    private var schoolCode: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    schoolCode = intent.getStringExtra(EXTRA_MESSAGE).toString()
                    payFeeEnterStudentNoScreen()
                }
            }
        }
    }


    @Composable
    fun payFeeEnterStudentNoScreen() {
        //Function Local Variables
        val obj = LocalContext.current
        var studentRegNo by remember { mutableStateOf("") }
        var enableButton by remember { mutableStateOf(false) }
        var buttonBgColor by remember { mutableStateOf(Color.LightGray) }
        var buttonContentColor by remember { mutableStateOf(Color.Black) }
        val obj1 = PublicClass()
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
                        Icon(
                            imageVector = Icons.Filled.House,
                            contentDescription = "School",
                        modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(
                                shape = CircleShape,
                                color = Color.LightGray
                            )
                            .size(70.dp),
                        )
                        Text(
                            text = "School Name",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "School Code:$schoolCode",
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

                OutlinedTextField(value = studentRegNo,
                    onValueChange = { studentRegNo = it },
                    //isError = emailIsError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Default
                    ),
                    label = { Text("Enter student's Reg No") },
                    placeholder = { Text(text = "Enter Your student's Reg_No") },
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    ),
                    // trailingIcon = {
                    // Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Address")
                    // },
                    //modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                )
                if (studentRegNo.length >= 1) {
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
                    onClick = { startActivity(Intent(obj,
                            PayFeeEnterAccountNo::class.java).apply {
                        putExtra(EXTRA_MESSAGE,studentRegNo)
                    }) },
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