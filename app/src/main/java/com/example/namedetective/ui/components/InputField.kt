package com.example.namedetective.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namedetective.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    onSearch: () -> Unit,
    onTextChanged: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {

    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text(
                stringResource(id = R.string.card_input_placeholder),
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray
            )
        },

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {onSearch(); localFocusManager.clearFocus()}),
        trailingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "search",
                modifier = Modifier.size(35.dp)
            )
        },
    )
}


@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    InputField(value = "Rust", onSearch = {}, onTextChanged = {})
}

