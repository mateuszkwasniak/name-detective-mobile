package com.example.namedetective.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.namedetective.R

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.namedetective.navigation.Routes
import com.example.namedetective.ui.components.GoSearchButton
import com.example.namedetective.ui.components.HeroSection
import com.example.namedetective.ui.components.TopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onGoSearchBtnClick: () -> Unit,
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
        HeroSection()
        GoSearchButton(
            onClick = onGoSearchBtnClick,
            modifier = Modifier.fillMaxWidth()
        )
    }


}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({})
}