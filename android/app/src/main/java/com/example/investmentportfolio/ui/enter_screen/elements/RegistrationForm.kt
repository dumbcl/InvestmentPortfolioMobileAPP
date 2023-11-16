package com.example.investmentportfolio.ui.enter_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        val context = LocalContext.current
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp),
        ) {
            Icon(
                Icons.Filled.KeyboardArrowLeft,
                "go back",
                tint = AppTheme.colors.deepBlue,
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp),
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = context.resources.getString(R.string.registration),
                color = AppTheme.colors.mainGreen,
                style = AppTheme.typography.smallTitle,
            )
            Spacer(modifier = Modifier.height(44.dp))

            Text(
                text = context.resources.getString(R.string.email_registration_form_hint),
                color = AppTheme.colors.mainGrey,
                style = AppTheme.typography.smallText,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            var email by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(306.dp),
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail
                },
                textStyle = AppTheme.typography.smallText,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = AppTheme.colors.mainGrey,
                    containerColor = AppTheme.colors.supportGreen,
                    unfocusedBorderColor = AppTheme.colors.mainGreen,
                    focusedBorderColor = AppTheme.colors.mainGreen
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = context.resources.getString(R.string.login_registration_form_hint),
                color = AppTheme.colors.mainGrey,
                style = AppTheme.typography.smallText,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            var emailRep by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(306.dp),
                value = emailRep,
                onValueChange = { newEmailRep ->
                    emailRep = newEmailRep
                },
                textStyle = AppTheme.typography.smallText,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = AppTheme.colors.mainGrey,
                    containerColor = AppTheme.colors.supportGreen,
                    unfocusedBorderColor = AppTheme.colors.mainGreen,
                    focusedBorderColor = AppTheme.colors.mainGreen
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = context.resources.getString(R.string.password_registration_form_hint),
                color = AppTheme.colors.mainGrey,
                style = AppTheme.typography.smallText,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            var password by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(306.dp),
                value = password,
                onValueChange = { newPassword ->
                    password = newPassword
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = AppTheme.typography.smallText,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = AppTheme.colors.mainGrey,
                    containerColor = AppTheme.colors.supportGreen,
                    unfocusedBorderColor = AppTheme.colors.mainGreen,
                    focusedBorderColor = AppTheme.colors.mainGreen
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = context.resources.getString(R.string.password_rep_registration_form_hint),
                color = AppTheme.colors.mainGrey,
                style = AppTheme.typography.smallText,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(6.dp))
            var passwordRep by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(306.dp),
                value = passwordRep,
                onValueChange = { newPasswordRep ->
                    passwordRep = newPasswordRep
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = AppTheme.typography.smallText,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = AppTheme.colors.mainGrey,
                    containerColor = AppTheme.colors.supportGreen,
                    unfocusedBorderColor = AppTheme.colors.mainGreen,
                    focusedBorderColor = AppTheme.colors.mainGreen
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )


            Spacer(modifier = Modifier.height(25.dp))
            TextButton(
                onClick = {},
                modifier = Modifier
                    .width(188.dp)
                    .height(49.dp)
                    .background(
                        color = AppTheme.colors.mainGreen,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            ) {
                Text(
                    text = context.resources.getString(R.string.register),
                    color = AppTheme.colors.white,
                    style = AppTheme.typography.smallText
                )
            }
        }
    }
}