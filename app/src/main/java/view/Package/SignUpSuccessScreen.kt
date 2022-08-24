package view.Package

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import view.Package.ReusableFunctions.backArrow

@Composable
fun successSignUp(navController: NavController){
    backArrow(navController = navController)
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Account created successfull",
        style = MaterialTheme.typography.h1)
        Text(text = "check your email to verify the account",
        color = Color(30, 144, 255),
            textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable {
            // open the email app
        })
    }
}