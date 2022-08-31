package view.Package

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import view.Package.ReusableFunctions.backArrow

@Composable
fun enterStudentNoScreen(navController: NavController, channel:String, schoolCode:String) {
    //Function Local Variables
    var studentRegNo by remember { mutableStateOf("") }
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
                    Icon(
                        imageVector = Icons.Filled.House,
                        contentDescription = "School",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(
                                shape = CircleShape,
                                color = Color.LightGray
                            )
                            .size(70.dp),
                    )
                    Text(
                        text = "School Name",
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = "School Code:$schoolCode",
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

            OutlinedTextField(value = studentRegNo,
                onValueChange = { studentRegNo = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Default
                ),
                label = { Text("Enter student's Reg No") },
                placeholder = { Text(text = "Enter Your student's Reg_No") },
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
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
            payNavButton(
                onClick = {
                          // navigating to enter account No screen
                    navController.navigate("enterAccountScreen/$channel/$schoolCode/$studentRegNo")
                },
                enabled = enableButton,
                text = "Continue",
                bgColor = buttonBgColor,
                contentColor = buttonContentColor
            )
        }
    }


}