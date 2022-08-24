package view.Package.ReusableFunctions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun roundButton(onClick: () -> Unit,imageId:Int,imageDes:String,text:String, color: Color){
    Column() {

        Button(
            onClick = onClick,
            shape = CircleShape,
            modifier = Modifier.size(70.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = color,
                contentColor = Color.White
            )
        ) {
            Image(
                painter =
                painterResource(id = imageId),
                contentDescription = imageDes,
                modifier = Modifier.size(50.dp)
            )
        }
        Text(text = text)
    }
}