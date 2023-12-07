package com.example.namedetective.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namedetective.R

@Composable
fun GoSearchButton(onClick: () -> Unit, modifier: Modifier = Modifier){
    ElevatedButton(onClick = { onClick() }, colors = ButtonDefaults.elevatedButtonColors(
        containerColor = Color.DarkGray,
        contentColor = Color.White), modifier = modifier, shape = MaterialTheme.shapes.small
    ) {
        Text(text = stringResource(id = R.string.go_search), fontSize = 24.sp, letterSpacing = 3.sp)
    }
}


@Preview
@Composable
fun GoSearchButtonPreview(){
    GoSearchButton(onClick = {})
}