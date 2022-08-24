package view.Package.ReusableFunctions

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun customCardChannel(navController: NavController, imageId:Int, imageDes:String, text:String){
    val obj = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .padding(start = 16.dp, end = 16.dp)
        .clickable {
            // navigating to school code screen
            navController.navigate("schoolCodeScreen/$imageDes")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = imageDes
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text)
        }
    }
}