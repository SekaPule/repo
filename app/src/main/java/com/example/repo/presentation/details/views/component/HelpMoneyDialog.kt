package com.example.repo.presentation.details.views.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repo.R
import com.example.repo.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpMoneyDialog(
    isShowDialog: Boolean,
    setShowDialog: () -> Unit,
    selectedDonationButton: DonationButtons,
    setSelectedButton: (donationButton: DonationButtons) -> Unit,
    modifier: Modifier = Modifier
) {
    if (isShowDialog) {
        AlertDialog(
            containerColor = AppTheme.colors.white,
            shape = RoundedCornerShape(size = 2.dp),
            title = {
                Text(
                    text = stringResource(id = R.string.choose_money_header),
                    style = AppTheme.typography.textDialogHeader,
                    color = AppTheme.colors.black87,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.choose_money_lable),
                        fontSize = 16.sp,
                        color = AppTheme.colors.black60
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(id = R.dimen.small_content_spacing),
                                bottom = dimensionResource(id = R.dimen.spacing_l)
                            ),
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(0.25f),
                            onClick = { setSelectedButton.invoke(DonationButtons.MIN) },
                            colors = if (selectedDonationButton == DonationButtons.MIN) {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.lightGreen,
                                    contentColor = AppTheme.colors.white
                                )
                            } else {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.white,
                                    contentColor = AppTheme.colors.lightGreen
                                )
                            },
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = AppTheme.colors.lightGreen
                            ),
                            shape = RectangleShape
                        ) {
                            Text(
                                text = "500",
                                style = AppTheme.typography.textDialogButton
                            )
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth(0.25f),
                            onClick = { setSelectedButton.invoke(DonationButtons.LOW) },
                            colors = if (selectedDonationButton == DonationButtons.LOW) {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.lightGreen,
                                    contentColor = AppTheme.colors.white
                                )
                            } else {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.white,
                                    contentColor = AppTheme.colors.lightGreen
                                )
                            },
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = AppTheme.colors.lightGreen
                            ),
                            shape = RectangleShape
                        ) {
                            Text(
                                text = "1000",
                                style = AppTheme.typography.textDialogButton
                            )
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth(0.25f),
                            onClick = { setSelectedButton.invoke(DonationButtons.MID) },
                            colors = if (selectedDonationButton == DonationButtons.MID) {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.lightGreen,
                                    contentColor = AppTheme.colors.white
                                )
                            } else {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.white,
                                    contentColor = AppTheme.colors.lightGreen
                                )
                            },
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = AppTheme.colors.lightGreen
                            ),
                            shape = RectangleShape
                        ) {
                            Text(
                                text = "1500",
                                style = AppTheme.typography.textDialogButton
                            )
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth(0.25f),
                            onClick = { setSelectedButton.invoke(DonationButtons.HIGH) },
                            colors = if (selectedDonationButton == DonationButtons.HIGH) {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.lightGreen,
                                    contentColor = AppTheme.colors.white
                                )
                            } else {
                                ButtonDefaults.outlinedButtonColors(
                                    containerColor = AppTheme.colors.white,
                                    contentColor = AppTheme.colors.lightGreen
                                )
                            },
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = AppTheme.colors.lightGreen
                            ),
                            shape = RectangleShape
                        ) {
                            Text(
                                text = "2000",
                                style = AppTheme.typography.textDialogButton
                            )
                        }
                    }

                    Text(
                        text = stringResource(id = R.string.input_money_lable),
                        fontSize = 16.sp,
                        color = AppTheme.colors.black60
                    )

                    TextField(
                        value = TextFieldValue(),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.input_money_placeholder),
                                fontSize = 16.sp,
                                color = AppTheme.colors.black38,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            cursorColor = AppTheme.colors.lightGreen,
                            focusedIndicatorColor = AppTheme.colors.lightGreen,
                            unfocusedIndicatorColor = AppTheme.colors.black12
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            },
            onDismissRequest = { setShowDialog.invoke() },
            confirmButton = {
                Button(
                    onClick = { setShowDialog.invoke() },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = AppTheme.colors.lightGreen
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_dismiss_btn_text).uppercase(),
                        style = AppTheme.typography.textDialogButton
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { setShowDialog.invoke() },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = AppTheme.colors.lightGreen
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_transact_btn_text).uppercase(),
                        style = AppTheme.typography.textDialogButton
                    )
                }
            }
        )
    }
}