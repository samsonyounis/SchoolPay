package view.Package

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import view.Package.ReusableFunctions.backArrow

@Composable
fun enterAccountScreen(navController: NavController, channel:String,
                               schoolCode:String, studentNo:String) {
    //Function Local Variables
    var AccountNo by remember { mutableStateOf("") }
    var label by remember { mutableStateOf("") }
    var placeholder by remember { mutableStateOf("") }
    var enableButton by remember { mutableStateOf(false) }
    var buttonBgColor by remember { mutableStateOf(Color.LightGray) }
    var buttonContentColor by remember { mutableStateOf(Color.Black) }

    if (channel == "mpesa"){
        label = "Enter your mpesa number"
        placeholder = "mpesa number"
    }
    else if (channel == "airtel"){
        label = "Enter your airtel number"
        placeholder = "Airtel number"
    }
    else if (channel== "telkom"){
        label = "Enter your t-Kash number"
        placeholder = "telkom Number"
    }
    else {
        label = "Enter your bank account number"
        placeholder = "bank acount No"
    }
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                backArrow(navController = navController)
                Column {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Student",
                        modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(
                                shape = CircleShape,
                                color = Color.Red
                            )
                            .size(70.dp),
                        colorResource(id = R.color.white)
                    )
                    Text(
                        text = "Student's Name",
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = "Reg_No:$studentNo",
                        fontSize = 16.sp,
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

            OutlinedTextField(value = AccountNo,
                onValueChange = { AccountNo = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Default
                ),
                label = { Text(text = label) },
                placeholder = { Text(text = placeholder) },
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                shape = RoundedCornerShape(20.dp)
            )
            if (AccountNo.length >= 10) {
                enableButton = true
                buttonBgColor = Color(30, 144, 255)
                buttonContentColor = Color.White
            } else {
                enableButton = false
                buttonBgColor = Color.LightGray
                buttonContentColor = Color.Black
            }

            payNavButton(
                onClick = {
                    // navigating to enter amount screen
                    navController.navigate(
                        "enterAmountScreen/$channel/$schoolCode/$studentNo/$AccountNo")
                },
                enabled = enableButton,
                text = "Continue",
                bgColor = buttonBgColor,
                contentColor = buttonContentColor
            )
        }


    }
}