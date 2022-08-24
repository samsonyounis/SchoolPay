package view.Package

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.AlarmClock
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.phone.SmsRetriever
import view.Package.ui.theme.SchoolPayTheme
import java.util.regex.Pattern

class PayFeeOTPActivity : ComponentActivity() {
    private val REQ_USER_CONSENT by lazy { 20}
    // creating instance of Broadcast reciever
    var smsBroadcastReciever:SmsBroadCastReciever? = null
    // creating the varible to hold the Otp Code
    private  var otpCode = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    otpScreen()
                    startSmartUserConsent()
                }
            }
        }
    }
// function to start listening to the incoming message containg OTP from server
    private fun startSmartUserConsent(){
        val client = SmsRetriever.getClient(this)
       client.startSmsUserConsent(null).addOnSuccessListener {
           Log.d("Result" ,"listening success")
       }.addOnFailureListener {
           Log.d("Result","Listening failed")
       }
    }
//function to register the Broadcasst reciever in the main activity.
private fun registerBroadCastReciever(){
    //assigning the SmsBroadCastReciever object to the smsBroadcastReciever var
    smsBroadcastReciever = SmsBroadCastReciever()
    smsBroadcastReciever!!.smsBroadcastRecieverListener =
        object : SmsBroadCastReciever.SmsBroadcastRecieverListener{
            override fun onSuccess(intent: Intent?) {
                startActivityForResult(intent,REQ_USER_CONSENT)
            }

            override fun onFailure() {
                // through toeast or restart listening
                TODO("Not yet implemented")
            }

        }
    val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
    registerReceiver(smsBroadcastReciever, intentFilter)
}

    override fun onStart() {
        super.onStart()
        registerBroadCastReciever()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsBroadcastReciever)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_USER_CONSENT -> {
                if ((resultCode == Activity.RESULT_OK) && (data != null)) {
                    //That gives all message to us. We need to get the code from inside with regex
                    val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    getOtpCodeFromMessage(message)
                }
            }
        }
    }
 //Function to extract the Otp code from the incoming message
private fun getOtpCodeFromMessage(message:String?){
    val otpPattern = Pattern.compile("[0-9]{6}")
    val matcher = otpPattern.matcher(message)
    if(matcher.find()){
        otpCode = matcher.group(0)
    }
     else{
         otpCode = "No code format found in SMS"
     }
}
    @Composable
    fun otpScreen() {
        //Function Local Variables
        val obj = LocalContext.current
        var otpCode by remember { mutableStateOf(otpCode) }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(value = otpCode,
                    onValueChange = { otpCode = it },
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
                if (otpCode.length >= 6) {
                    enableButton = true
                    buttonBgColor = Color(30, 144, 255)
                    buttonContentColor = Color.White
                } else {
                    enableButton = false
                    buttonBgColor = Color.LightGray
                    buttonContentColor = Color.Black
                }
                payNavButton(
                    onClick = { },
                    enabled = enableButton,
                    text = "Continue",
                    bgColor = buttonBgColor,
                    contentColor = buttonContentColor
                )
            }
        }

    }
}