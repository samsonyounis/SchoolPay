package view.Package

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import view.Package.ui.theme.SchoolPayTheme

class AddDependantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    addDependantScreen()
                }
            }
        }
    }


    @Composable
    fun addDependantScreen() {
        //Function local Variables
        val obj = LocalContext.current
        var reg_No by remember { mutableStateOf("") }
        var schoolName by remember { mutableStateOf("") }
        Scaffold(modifier = Modifier.padding(bottom = 10.dp),
            topBar = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {/*TODO*/ },
                        modifier = Modifier.align(Alignment.Start)) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go to previous screen")
                    }
                    Text(text = "Add Dependant",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 24.sp)
                    Text(text = "Enter thier Registration No and School name")
                }
            },
            bottomBar= {
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(30,144,255),
                        contentColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp).padding(start = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(15.dp)) {
                    Text(text = "Save")
                }
            }

        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

                TextField(value = reg_No, onValueChange ={reg_No = it},
                    label = { Text(text = "Reg no")},
                    placeholder = { Text(text = "E.g 677")})
                Spacer(modifier = Modifier.height(30.dp))

                TextField(value = schoolName, onValueChange ={schoolName = it},
                    label = { Text(text = "school")},
                    placeholder = { Text(text = "E.g Moi Forces Academy")})

            }
        }


    }
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolPayTheme {
        Greeting("Android")
    }
}
 */