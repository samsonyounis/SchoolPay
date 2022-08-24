package view.Package

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.phone.SmsRetriever
import repository.Repository
import view.Package.ui.theme.SchoolPayTheme
import viewModel.Packag.LoginScreenViewModel
import viewModel.Packag.LoginScreenViewModelFactory
import viewModel.Packag.SignUpScreenViewModel
import viewModel.Packag.SignUpScreenViewModelFactory
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    private val REQ_USER_CONSENT by lazy { 20}
    // creating instance of Broadcast reciever
    var smsBroadcastReciever:SmsBroadCastReciever? = null
    // creating the varible to hold the Otp Code
   private var otpCode = ""
    // creating Variable of type navController
    private lateinit var navController:NavHostController
    // Instance of repository
    val repository by lazy { Repository() }
    // instance of loginScreen ViewModelFactory
    val loginViewModelFactory by lazy { LoginScreenViewModelFactory(repository) }
    val loginViewModel by lazy { ViewModelProvider(this,loginViewModelFactory).get(
        LoginScreenViewModel::class.java)
    }
    //instance of sign up screen view model
    val signUpViewModelFactory by lazy { SignUpScreenViewModelFactory(repository) }
    val signUpViewModel by lazy { ViewModelProvider(this,signUpViewModelFactory).get(
        SignUpScreenViewModel::class.java
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                // initializing the navController
                navController = rememberNavController()
                // seeting up the NavHost Graph.
                NavHost(
                    navController = navController,
                    startDestination = "animateSplashScreen_Screen"
                ) {
                    composable("welcome_Screen"
                    ) {
                        welcomeScreen(navController)
                    }

                    composable("animateSplashScreen_Screen") {
                        animateSplashScreen(navController)
                    }
                    composable("loginScreen") {
                        loginScreen(navController, loginViewModel)
                    }
                    composable("signUpScreen") {
                        signUpScreen(navController, signUpViewModel)
                    }
                    composable("errorScreen/{error}",
                    arguments = listOf(
                        navArgument("error"){type = NavType.StringType}
                    )) {
                        val error = it.arguments?.getString("error").toString()
                        errorScreen(navController, error)
                    }
                    composable("homeScreen"){
                        homeScreen(navController = navController)
                    }
                    composable("notificationScreen"){
                        notificationsScreen(navController = navController)
                    }
                    composable("addDependantScreen"){
                        addDependantScreen(navController = navController)
                    }
                    composable("selectPayChannelScreen"){
                        selectChannelScreen(navController = navController)
                    }
                    composable("schoolCodeScreen/{payChannel}",
                    arguments = listOf(
                        navArgument("payChannel"){type = NavType.StringType}
                    )){
                        val code = it.arguments?.getString("payChannel").toString()
                        schoolCodeScreen(navController = navController,code)
                    }

                    composable("studentNoScreen/{payChannel}/{schoolCode}",
                        arguments = listOf(
                            navArgument("payChannel"){type = NavType.StringType},
                            navArgument("schoolCode"){type = NavType.StringType}
                        )){
                        val payChannel = it.arguments?.getString("payChannel").toString()
                        val schoolCode = it.arguments?.getString("schoolCode").toString()
                        enterStudentNoScreen(navController = navController, channel = payChannel,
                            schoolCode = schoolCode)
                    }
                    composable("enterAccountScreen/{payChannel}/{schoolCode}/{studentNo}",
                        arguments = listOf(
                            navArgument("payChannel"){type = NavType.StringType},
                            navArgument("schoolCode"){type = NavType.StringType},
                            navArgument("studentNo"){type = NavType.StringType}
                        )){
                        val payChannel = it.arguments?.getString("payChannel").toString()
                        val schoolCode = it.arguments?.getString("schoolCode").toString()
                        val studentNo = it.arguments?.getString("studentNo").toString()
                        enterAccountScreen(navController = navController, channel = payChannel,
                            schoolCode = schoolCode, studentNo = studentNo)
                    }
                    composable("enterAmountScreen/{payChannel}/{schoolCode}/{studentNo}/{account}",
                        arguments = listOf(
                            navArgument("payChannel"){type = NavType.StringType},
                            navArgument("schoolCode"){type = NavType.StringType},
                            navArgument("studentNo"){type = NavType.StringType},
                            navArgument("account"){type = NavType.StringType}
                        )){
                        val payChannel = it.arguments?.getString("payChannel").toString()
                        val schoolCode = it.arguments?.getString("schoolCode").toString()
                        val studentNo = it.arguments?.getString("studentNo").toString()
                        val accountNo = it.arguments?.getString("account").toString()
                        enterAmountScreen(navController = navController, channel = payChannel,
                            schoolCode = schoolCode, studentNO = studentNo, accountNo = accountNo)
                    }
                    composable(
                        "feeOtpScreen/{payChannel}/{schoolCode}/{studentNo}/{account}/{amount}",
                        arguments = listOf(
                            navArgument("payChannel"){type = NavType.StringType},
                            navArgument("schoolCode"){type = NavType.StringType},
                            navArgument("studentNo"){type = NavType.StringType},
                            navArgument("account"){type = NavType.StringType},
                            navArgument("amount"){type = NavType.StringType}
                        )){
                        val payChannel = it.arguments?.getString("payChannel").toString()
                        val schoolCode = it.arguments?.getString("schoolCode").toString()
                        val studentNo = it.arguments?.getString("studentNo").toString()
                        val accountNo = it.arguments?.getString("account").toString()
                        val amount = it.arguments?.getString("amount").toString()
                        otpScreen(navController = navController, channel = payChannel,
                            schoolCode = schoolCode, studentNo = studentNo, accountNo = accountNo,
                        amount = amount)
                    }
                    composable(
                        "confirmScreen/{payChannel}/{schoolCode}/{studentNo}/{account}/{amount}",
                        arguments = listOf(
                            navArgument("payChannel"){type = NavType.StringType},
                            navArgument("schoolCode"){type = NavType.StringType},
                            navArgument("studentNo"){type = NavType.StringType},
                            navArgument("account"){type = NavType.StringType},
                            navArgument("amount"){type = NavType.StringType}
                        )){
                        val payChannel = it.arguments?.getString("payChannel").toString()
                        val schoolCode = it.arguments?.getString("schoolCode").toString()
                        val studentNo = it.arguments?.getString("studentNo").toString()
                        val accountNo = it.arguments?.getString("account").toString()
                        val amount = it.arguments?.getString("amount").toString()
                        confirmationScreen(navController = navController, channel = payChannel,
                            schoolCode = schoolCode, studentNo = studentNo, accountNo = accountNo,
                        amount = amount)
                    }
                    composable("forgetPasswordScreen"){
                        forgotPasswordScreen(navController = navController)
                    }
                    composable("signUpSccessScreen"){
                        successSignUp(navController = navController)
                    }
                }
            }
        }
        }
    }
}
