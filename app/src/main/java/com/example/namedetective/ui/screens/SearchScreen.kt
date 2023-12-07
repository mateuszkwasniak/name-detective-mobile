package com.example.namedetective.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.namedetective.R
import com.example.namedetective.data.api.convertNameDataToReadable
import com.example.namedetective.ui.components.InputField
import com.example.namedetective.ui.model.AppViewModel
import com.example.namedetective.ui.model.state.AppUiState


@Composable
fun SearchScreen(
    onCheckHistoryBtnClick: () -> Unit,
    appViewModel: AppViewModel,
    appUiState: AppUiState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = R.dimen.padding_screen_top),
                bottom = dimensionResource(
                    id = R.dimen.padding_screen_bottom
                )
            )
            .padding(horizontal = dimensionResource(id = R.dimen.padding_screen_horizontal)),

        verticalArrangement = Arrangement.SpaceBetween


    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.search),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 30.sp,
                color = Color.DarkGray,
                modifier = Modifier

                    .fillMaxWidth(0.7f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                InputField(value = appViewModel.nameEntered, onSearch = {
                    appViewModel.fetchNameDetails()
                }, onTextChanged = {
                    appViewModel.updateNameEntered(it.trim())
                })
                Spacer(modifier = Modifier.width(20.dp))

            }
            Spacer(modifier = Modifier.height(40.dp))
            if (appViewModel.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
            }
            if (appUiState.searchResult != null && appUiState.searchResult?.country?.size != 0) {
                Text(
                    text = convertNameDataToReadable(appUiState.searchResult!!),
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
            if (appUiState.searchError != "") {
                Text(text = appUiState.searchError)
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        ElevatedButton(
            onClick = onCheckHistoryBtnClick,
            modifier = Modifier.alpha(if (appUiState.history.isNotEmpty()) 1.0f else 0.0f),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.DarkGray, contentColor = Color.White
            ),
            shape = RoundedCornerShape(10)
        )
        {
            Text(text = "Check history")
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier.weight(0.4f)) {
            }

            Image(
                painter = painterResource(id = R.drawable.search_girl),
                contentDescription = "girl",
                modifier = Modifier
                    .weight(0.5f)
                    .alpha(if (appUiState.history.isNotEmpty()) 0.0f else 1.0f)
            )
        }

    }


}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen({}, viewModel(), AppUiState())
}