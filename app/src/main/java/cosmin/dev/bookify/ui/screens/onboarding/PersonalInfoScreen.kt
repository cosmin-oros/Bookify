package cosmin.dev.bookify.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.bookify.R
import cosmin.dev.bookify.data.SharedPreferencesManager
import cosmin.dev.bookify.navigation.Screen

@Composable
fun PersonalInfoScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var ageError by remember { mutableStateOf(false) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastNameError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp), // Add bottom padding to push the content up
        verticalArrangement = Arrangement.SpaceBetween, // Use SpaceBetween to position elements at top and bottom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center, // Center the top content vertically
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_falcon),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Please enter your personal info",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                    firstNameError = false // Reset the first name error state when the input changes
                },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = firstNameError, // Apply the error state to the TextField
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (firstNameError) Color.Red else Color.Cyan, // Customize the outline color for error state
                    unfocusedIndicatorColor = if (firstNameError) Color.Red else Color.Cyan // Customize the outline color for error state
                )
            )

            if (firstNameError) {
                Text(
                    text = "Please enter a valid first name",
                    color = Color.Red,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                    lastNameError = false // Reset the last name error state when the input changes
                },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = lastNameError, // Apply the error state to the TextField
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (lastNameError) Color.Red else Color.Cyan, // Customize the outline color for error state
                    unfocusedIndicatorColor = if (lastNameError) Color.Red else Color.Cyan // Customize the outline color for error state
                )
            )

            if (lastNameError) {
                Text(
                    text = "Please enter a valid last name",
                    color = Color.Red,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = age,
                onValueChange = {
                    age = it
                    ageError = false // Reset the age error state when the input changes
                },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = ageError, // Apply the error state to the TextField
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (ageError) Color.Red else Color.Cyan, // Customize the outline color for error state
                    unfocusedIndicatorColor = if (ageError) Color.Red else Color.Cyan // Customize the outline color for error state
                )
            )

            if (ageError) {
                Text(
                    text = "Please enter a valid age",
                    color = Color.Red,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Button(
            onClick = {
                if (isNameValid(firstName) && isNameValid(lastName) && age.isNotBlank() && age.toIntOrNull() != null) {
                    navController.navigate(Screen.ReadingLevelScreen.route)
                } else {
                    ageError = true // Set the age error state if the input is invalid
                    firstNameError = !isNameValid(firstName) // Set the first name error state if the input is invalid
                    lastNameError = !isNameValid(lastName) // Set the last name error state if the input is invalid
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
            )
        }
    }

}

fun isNameValid(name: String): Boolean {
    return name.matches(Regex("[a-zA-Z]+")) // Check if the name contains only letters
}