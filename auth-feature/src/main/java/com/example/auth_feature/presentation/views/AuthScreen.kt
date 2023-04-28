package com.example.auth_feature.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auth_feature.R
import com.example.auth_feature.presentation.theme.AppTheme
import com.example.auth_feature.presentation.vm.AuthIntent
import com.example.auth_feature.presentation.vm.AuthScreenViewModel
import com.example.auth_feature.presentation.vm.AuthState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewModel: AuthScreenViewModel
) {
    val viewState = viewModel.stateObservable.observeAsState(initial = AuthState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arrow_back_description),
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(id = R.dimen.spacing_xs))
                            .clickable {
                                viewModel.obtainIntent(AuthIntent.CloseIntent)
                            }
                    )
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.auth_title),
                        style = AppTheme.typography.textStyle2
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = AppTheme.colors.white,
                    containerColor = AppTheme.colors.lightGreen,
                    navigationIconContentColor = AppTheme.colors.white
                )

            )
        },
        containerColor = AppTheme.colors.white
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_l))
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.auth_invite_text),
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        top = dimensionResource(id = R.dimen.auth_invite_margin_top),
                        end = 0.dp,
                        bottom = 0.dp
                    )
                    .align(Alignment.CenterHorizontally),
                fontSize = 14.sp,
                color = AppTheme.colors.black70,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        start = 0.dp,
                        top = dimensionResource(id = R.dimen.spacing_l),
                        end = 0.dp,
                        bottom = 0.dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_vk),
                    contentDescription = stringResource(
                        id = R.string.vk_logo_description
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_fb),
                    contentDescription = stringResource(
                        id = R.string.fb_logo_description
                    ),
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.auth_invite_margin_top))
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_ok),
                    contentDescription = stringResource(
                        id = R.string.ok_logo_description
                    )
                )
            }

            Text(
                text = stringResource(id = R.string.auth_invite_text2), modifier = Modifier
                    .padding(
                        start = 0.dp,
                        top = dimensionResource(id = R.dimen.auth_invite_margin_top),
                        end = 0.dp,
                        bottom = 0.dp
                    )
                    .align(Alignment.CenterHorizontally),
                fontSize = 14.sp,
                color = AppTheme.colors.black70
            )

            Text(
                text = stringResource(id = R.string.email_label),
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        top = dimensionResource(id = R.dimen.spacing_l),
                        end = 0.dp,
                        bottom = 0.dp
                    ),
                fontSize = 12.sp,
                color = AppTheme.colors.black38
            )

            TextField(
                value = viewState.value.email,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.email_edit_text_hint),
                        fontSize = 16.sp,
                        color = AppTheme.colors.black38
                    )
                },

                onValueChange = {
                    viewModel.obtainIntent(
                        AuthIntent.EmailChangedIntent(
                            email = it
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = AppTheme.colors.lightGreen,
                    focusedIndicatorColor = AppTheme.colors.lightGreen,
                    unfocusedIndicatorColor = AppTheme.colors.black12
                )
            )

            Text(
                text = stringResource(id = R.string.password_label),
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        top = dimensionResource(id = R.dimen.spacing_l),
                        end = 0.dp,
                        bottom = 0.dp
                    ),
                fontSize = 12.sp,
                color = AppTheme.colors.black38
            )

            var showPassword by remember { mutableStateOf(false) }
            TextField(
                value = viewState.value.password,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password_edit_text_hint),
                        fontSize = 16.sp,
                        color = AppTheme.colors.black38
                    )
                },
                onValueChange = {
                    viewModel.obtainIntent(
                        AuthIntent.PasswordChangedIntent(
                            password = it
                        )
                    )
                },
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_open),
                                contentDescription = null
                            )
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = AppTheme.colors.lightGreen,
                    focusedIndicatorColor = AppTheme.colors.lightGreen,
                    unfocusedIndicatorColor = AppTheme.colors.black12
                )
            )

            Button(
                onClick = {
                    viewModel.obtainIntent(AuthIntent.LoginIntent)
                },
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        top = dimensionResource(id = R.dimen.auth_btn_top_margin),
                        end = 0.dp,
                        bottom = 0.dp
                    )
                    .fillMaxWidth(),
                enabled = viewState.value.isValid,
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colors.lightGreen,
                    contentColor = AppTheme.colors.white,
                )
            ) {
                Text(
                    text = stringResource(id = R.string.auth_btn_text).uppercase(),
                    style = AppTheme.typography.textStyle17
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(
                        start = 0.dp,
                        top = dimensionResource(id = R.dimen.spacing_l),
                        end = 0.dp,
                        bottom = 0.dp
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password_btn_text),
                    color = AppTheme.colors.lightGreen,
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(id = R.string.register_btn_text),
                    color = AppTheme.colors.lightGreen,
                    fontSize = 14.sp
                )
            }
        }
    }
}