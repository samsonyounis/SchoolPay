package view.Package.ReusableFunctions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun backArrow(navController: NavController){
    Row(horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = {
            // navigating the back stack
            navController.navigateUp()
        }) {
            Icon(imageVector = Icons.Filled.ArrowBack,
                contentDescription ="Navigate back" )

        }
    }
}