package view.Package

import android.content.Intent
import android.provider.AlarmClock
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import view.Package.ReusableFunctions.backArrow

@Composable
fun schoolCodeScreen(navController: NavController, channel:String) {
    //Function Local Variables
    val obj = LocalContext.current
    var schoolCode by remember { mutableStateOf("") }
    var enableButton by remember { mutableStateOf(false) }
    var buttonBgColor by remember { mutableStateOf(Color.LightGray) }
    var buttonContentColor by remember { mutableStateOf(Color.Black) }
    Scaffold(
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly) {
                backArrow(navController = navController)
                Text(
                    text = "School Code",
                    style = MaterialTheme.typography.h1
                )
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

            OutlinedTextField(value = schoolCode,
                onValueChange = { schoolCode = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Default
                ),
                label = { Text("Enter school code") },
                placeholder = { Text(text = "Enter Your school code") },
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                shape = RoundedCornerShape(20.dp)
            )
            if(schoolCode.length >=1 ){
                enableButton = true
                buttonBgColor = Color(30,144,255)
                buttonContentColor = Color.White
            }
            else{
                enableButton = false
                buttonBgColor = Color.LightGray
                buttonContentColor = Color.Black
            }

            payNavButton(
                onClick = { Log.d("Selected Channel",channel)
                    navController.navigate("studentNoScreen/$channel/$schoolCode")},
                enabled = enableButton,
                text = "Continue",
                bgColor = buttonBgColor,
                contentColor = buttonContentColor
            )
        }
    }


}