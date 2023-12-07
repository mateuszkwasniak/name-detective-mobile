package com.example.namedetective.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.namedetective.R

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview

import com.example.namedetective.data.api.NameData
import com.example.namedetective.ui.components.NameDataCard

import com.example.namedetective.ui.model.state.AppUiState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchHistoryScreen(searchHistory: List<NameData>, modifier: Modifier = Modifier) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = R.dimen.padding_screen_top),
                bottom = dimensionResource(
                    id = R.dimen.padding_screen_bottom
                )
            )
            .padding(horizontal = dimensionResource(id = R.dimen.padding_screen_horizontal)),


        ) {
        Text(
            text = stringResource(id = R.string.search_history_header),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Divider(
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        LazyColumn() {
            itemsIndexed(searchHistory) { index, item ->
                NameDataCard(
                    index == searchHistory.lastIndex,
                    item,
                    modifier = Modifier.animateItemPlacement(
                        tween(durationMillis = 250)

                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchHistoryScreenPreview() {
    SearchHistoryScreen(searchHistory = listOf())
}