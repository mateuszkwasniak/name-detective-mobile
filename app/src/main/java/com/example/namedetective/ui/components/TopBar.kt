package com.example.namedetective.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.namedetective.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(toggleDrawer: () -> Unit, modifier: Modifier = Modifier){
    TopAppBar(
        modifier = Modifier.shadow(5.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                Image(painter = painterResource(id = R.drawable.nd_logo), contentDescription = "logo", modifier = Modifier
                    .width(140.dp)
                    .height(60.dp))
                Icon(Icons.Filled.Menu, tint = Color.Black, contentDescription = "menu", modifier = Modifier.size(45.dp).padding(end = 10.dp).clickable { toggleDrawer() })
            }

        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
            )
}
//
//@Preview
//@Composable
//fun TopBarPreview(){
//    TopBar()
//}