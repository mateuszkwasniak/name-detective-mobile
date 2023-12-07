package com.example.namedetective

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.namedetective.navigation.Routes
import com.example.namedetective.ui.components.TopBar
import com.example.namedetective.ui.model.AppViewModel
import com.example.namedetective.ui.screens.HomeScreen
import com.example.namedetective.ui.screens.SearchHistoryScreen
import com.example.namedetective.ui.screens.SearchScreen
import com.example.namedetective.ui.theme.NameDetectiveTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameDetectiveTheme {
                NameDetectiveApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameDetectiveApp(
    appViewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val appUiState by appViewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val toggleDrawer: () -> Unit = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .background(color = MaterialTheme.colorScheme.background),
                drawerShape = RoundedCornerShape(0)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(color = Color.DarkGray)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.drawer_title),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }
                NavigationDrawerItem(

                    label = {
                        Column() {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Home, contentDescription = "home")
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Home", fontWeight = FontWeight.SemiBold)

                            }

                        }
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.HOME_SCREEN)
                        toggleDrawer()
                    }
                )
                Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth(0.75f))
                NavigationDrawerItem(
                    label = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Search, contentDescription = "search")
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Search", fontWeight = FontWeight.SemiBold)

                            }


                        }
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.SEARCH_SCREEN)
                        toggleDrawer()
                    }
                )
                Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth(0.75f))

                NavigationDrawerItem(
                    label = {
                        Column() {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.List, contentDescription = "history")
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Search history", fontWeight = FontWeight.SemiBold)

                            }

                        }
                    },


                    selected = false,
                    onClick = {
                        navController.navigate(Routes.SEARCH_HISTORY_SCREEN)
                        toggleDrawer()
                    }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(toggleDrawer = toggleDrawer) }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.HOME_SCREEN,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(Routes.HOME_SCREEN) {
                    HomeScreen(onGoSearchBtnClick = { navController.navigate(Routes.SEARCH_SCREEN) })
                }
                composable(Routes.SEARCH_SCREEN) {
                    SearchScreen(
                        onCheckHistoryBtnClick = { navController.navigate(Routes.SEARCH_HISTORY_SCREEN) },
                        appViewModel,
                        appUiState
                    )

                }
                composable(Routes.SEARCH_HISTORY_SCREEN) {
                    SearchHistoryScreen(searchHistory = appUiState.history)

                }

            }
        }
    }

}