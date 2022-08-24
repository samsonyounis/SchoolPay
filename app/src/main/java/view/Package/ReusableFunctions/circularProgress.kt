package view.Package.ReusableFunctions

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun circularProgressIndicator(showProgress:Boolean){
    if (showProgress == true){
        CircularProgressIndicator(
            color = Color(30, 144, 255,),
            strokeWidth = ProgressIndicatorDefaults.StrokeWidth)
    }
}