package view.Package

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun addDependantScreen(navController: NavController) {
    //Function local Variables
    var reg_No by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var studentName by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.padding(bottom = 10.dp),
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                                     // navigating the back stack.
                                     navController.navigateUp()
                },
                    modifier = Modifier.align(Alignment.Start)) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back")
                }
                Text(text = "Add Dependant",
                    style = MaterialTheme.typography.h1)
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
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Save")
            }
        }

    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            TextField(value = studentName, onValueChange ={studentName = it},
                label = { Text(text = "name") },
                placeholder = { Text(text = "E.g mary") })

            TextField(value = reg_No, onValueChange ={reg_No = it},
                label = { Text(text = "Reg no") },
                placeholder = { Text(text = "E.g 677") })
            Spacer(modifier = Modifier.height(30.dp))

            TextField(value = schoolName, onValueChange ={schoolName = it},
                label = { Text(text = "school") },
                placeholder = { Text(text = "E.g Moi Forces Academy") })



        }
    }


}