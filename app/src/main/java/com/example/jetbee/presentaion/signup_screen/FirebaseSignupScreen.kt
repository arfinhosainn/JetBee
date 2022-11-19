package com.example.jetbee.presentaion.signup_screen


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetbee.R
import com.example.jetbee.domain.model.AuthUser
import com.example.jetbee.navigation.Screens
import com.example.jetbee.presentaion.common.AuthenticationField
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.presentaion.signin_screen.FirebaseSignupViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavHostController,
    signUpViewModel: FirebaseSignupViewModel = hiltViewModel()
) {

    val signInState = signUpViewModel.signUpState.collectAsState(initial = null)

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.password_visible)
    } else {
        painterResource(id = R.drawable.password_toggle)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            text = "Welcome Back",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 35.sp
        )
        Text(
            text = "Log in to Continue",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp, color = Color.Gray
        )
        AuthenticationField(
            text = email,
            placeHolder = "Email",
            isPasswordTextField = false,
            onValueChange = { email = it },
            errorMsg = "*Enter valid email address",
            trailingIcon = {
                if (email.isNotBlank()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear Text")

                    }
                }
            })
        Spacer(modifier = Modifier.height(16.dp))
        AuthenticationField(
            text = password,
            placeHolder = "Password",
            isPasswordTextField = !passwordVisibility,
            onValueChange = { password = it },
            errorMsg = "*Enter valid password",
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon",
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp, top = 10.dp),
            text = "Forgot Password?",
            fontWeight = FontWeight.Bold, color = Color.Red
        )
        Button(
            onClick = {
                scope.launch(Dispatchers.Main) {
                    signUpViewModel.createUser(
                        AuthUser(
                            email, password
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 30.dp, end = 30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                modifier = Modifier.padding(7.dp),
                fontFamily = RegularFont,
                fontWeight = FontWeight.Medium
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            if (signInState.value?.isLoading == true) {
                CircularProgressIndicator()
            }
        }
        Text(
            modifier = Modifier
                .padding(15.dp),
            text = "Already have an account? Login",
            fontWeight = FontWeight.Bold, color = Color.Black
        )
        Text(
            modifier = Modifier
                .padding(
                    top = 40.dp,
                ),
            text = "Or connect with",
            fontWeight = FontWeight.Medium, color = Color.Gray
        )
    }



    LaunchedEffect(key1 = signInState.value?.error) {
        scope.launch(Dispatchers.Main) {
            if (signInState.value?.error?.isNotEmpty() == true) {
                val error = signInState.value?.error
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }

        }
    }

    LaunchedEffect(key1 = signInState.value?.isSignedIn) {
        if (signInState.value?.isSignedIn?.isNotEmpty() == true) {
            navController.popBackStack()
            val successful = signInState.value?.isSignedIn
            Toast.makeText(context, successful, Toast.LENGTH_LONG).show()
            navController.navigate(Screens.HomeScreen.route)
        }

    }


}
