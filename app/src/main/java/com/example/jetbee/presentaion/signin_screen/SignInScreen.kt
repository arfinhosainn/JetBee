package com.example.jetbee.presentaion.signin_screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.jetbee.R
import com.example.jetbee.domain.model.AuthUser
import com.example.jetbee.navigation.Screens
import com.example.jetbee.presentaion.common.AuthenticationField
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.util.Constant.SERVER_CLIENT_ID
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    oneTapSignInViewModel: OneTapSignInViewModel,
    signInViewModel: FirebaseSingInViewModel = hiltViewModel(),
    navController: NavController,
) {
    val googleState = oneTapSignInViewModel.googleSingInState.collectAsState()


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(account.idToken!!, null)
                oneTapSignInViewModel.signInWithGoogleCredentials(credentials, user = AuthUser())
            } catch (it: ApiException) {
                print(it)
            }
        }

    val signInState = signInViewModel.signInState.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val isUserExist = signInViewModel.currentUserExist.collectAsState(initial = true)
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.password_visible)
    } else {
        painterResource(id = R.drawable.password_toggle)
    }

    LaunchedEffect(key1 = Unit) {
        if (isUserExist.value) {
            navController.popBackStack()
            navController.navigate(
                Screens.HomeScreen.route
            )
        }
    }

    if (!isUserExist.value) {
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
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                fontFamily = RegularFont,
            )
            Text(
                text = "Log in to Continue",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp, color = Color.Gray,
                fontFamily = RegularFont,

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
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Text"
                            )

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
                fontWeight = FontWeight.SemiBold, color = Color.Red, fontFamily = RegularFont,

                )
            Button(
                onClick = {
                    scope.launch(Dispatchers.Main) {
                        signInViewModel.loginUser(
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
                    text = "Sign In",
                    color = Color.White,
                    modifier = Modifier
                        .padding(7.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (signInState.value?.isLoading == true) {
                    CircularProgressIndicator()
                }
            }
            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                          navController.navigate(
                              Screens.FireSignUpScreen.route
                          )
                    },
                text = "Don't have an account? sign up",
                fontWeight = FontWeight.Bold, color = Color.Black, fontFamily = RegularFont
            )
            Text(
                modifier = Modifier
                    .padding(
                        top = 40.dp,
                    ).clickable {
                        val gso = GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(SERVER_CLIENT_ID)
                            .requestEmail()
                            .build()
                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        launcher.launch(googleSignInClient.signInIntent)
                    },
                text = "Or connect with",
                fontWeight = FontWeight.Medium, color = Color.Gray
            )
        }

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
            navController.navigate(
                Screens.HomeScreen.route
            )
        }
    }

    LaunchedEffect(key1 = googleState.value.success) {
        if (googleState.value.success != null) {
            navController.popBackStack()
            val successful = googleState.value.success.toString()
            Toast.makeText(context, "successful", Toast.LENGTH_LONG).show()
            navController.navigate(
                Screens.HomeScreen.route
            )
        }
    }
    /*LaunchedEffect(key1 = googleState.error) {
        if (googleState.error?.isNotEmpty() == true) {
            navController.popBackStack()
            val error  = googleState.error
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            navController.navigate(
                Screens.HomeScreen.route
            )
        }
    }*/


}


/*
@Preview(showBackground = true)
@Composable
fun PrevSignInScreen() {
    SignInScreen(navController = NavHostController(LocalContext.current))
}*/
