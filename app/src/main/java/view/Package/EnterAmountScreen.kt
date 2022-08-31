package view.Package

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import view.Package.ReusableFunctions.backArrow

@Composable
fun enterAmountScreen(navController: NavController,channel:String,
    schoolCode:String, studentNO:String, accountNo:String) {
    //Function Local Variables
    var amount by remember { mutableStateOf("") }
    var enableButton by remember { mutableStateOf(false) }
    var buttonBgColor by remember { mutableStateOf(Color.LightGray) }
    var buttonContentColor by remember { mutableStateOf(Color.Black) }
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
                    Text(text = "GJ",
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier
                            .background(
                                shape = CircleShape,
                                color = Color.LightGray
                            )
                            .size(70.dp)
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center)
                    Text(
                        text = "Account Name",
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = "phone No:"+accountNo.substring(0,4)+"****"+
                                accountNo.substring(8),
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

            OutlinedTextField(value = amount,
                onValueChange = { amount = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Default
                ),
                label = { Text("Enter Amount") },
                placeholder = { Text(text = "Enter Amount to pay") },
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
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

            payNavButton(
                onClick = {navController.navigate(
                    "feeOtpScreen/$channel/$schoolCode/$studentNO/$accountNo/$amount")},
                enabled = enableButton,
                text = "Continue",
                bgColor = buttonBgColor,
                contentColor = buttonContentColor
            )
        }
    }


}