package view.Package

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.google.android.gms.auth.api.phone.SmsRetriever
import view.Package.ReusableFunctions.backArrow

@Composable
fun otpScreen(navController: NavController, channel:String, schoolCode:String,
 studentNo:String, accountNo:String, amount:String) {
    //Function Local Variables
    val obj = LocalContext.current
    val REQ_USER_CONSENT by lazy { 20}
    // creating instance of Broadcast reciever
    var smsBroadcastReciever:SmsBroadCastReciever? = null
    // lifeCycle owner
    val lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var otp by remember { mutableStateOf("") }
    var enableButton by remember { mutableStateOf(false) }
    var buttonBgColor by remember { mutableStateOf(Color.LightGray) }
    var buttonContentColor by remember { mutableStateOf(Color.Black) }

    DisposableEffect(lifeCycleOwner){
        val observer = LifecycleEventObserver{source, event ->
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
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

            OutlinedTextField(value = otp,
                onValueChange = { otp = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Default
                ),
                label = { Text("Enter OTP code ") },
                placeholder = { Text(text = "Enter the code send to your phone") },
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                shape = RoundedCornerShape(20.dp)
            )
            if (otp.length >= 6) {
                enableButton = true
                buttonBgColor = Color(30, 144, 255)
                buttonContentColor = Color.White
            } else {
                enableButton = false
                buttonBgColor = Color.LightGray
                buttonContentColor = Color.Black
            }
            payNavButton(
                onClick = {navController.navigate(
                    "confirmScreen/$channel/$schoolCode/$studentNo/$accountNo/$amount") },
                enabled = enableButton,
                text = "Continue",
                bgColor = buttonBgColor,
                contentColor = buttonContentColor
            )
        }
    }

}