package view.Package

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun animateSplashScreen(navController: NavController){
    var  startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        ))
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        navController.navigate("welcome_Screen")

    }
    splash(alphaAnim.value)
}
@Composable
fun splash(alpha: Float){
    Surface() {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.brand1),
                contentDescription ="Icon Logo" ,
                modifier = Modifier.size(300.dp)
                    .alpha(alpha))

        }
    }
}