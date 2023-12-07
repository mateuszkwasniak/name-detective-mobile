package com.example.namedetective.ui.components

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namedetective.data.api.NameData
import com.example.namedetective.data.api.convertCountryCodeToFullName

@Composable
fun NameDataCard(lastElement: Boolean, data: NameData, modifier: Modifier = Modifier){

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp).padding(top = 15.dp, bottom = if(lastElement) 10.dp else 0.dp)) {
            Text(text = "Name searched:", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = data.name, modifier = Modifier.padding(horizontal = 25.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Countries of origin:", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
            Spacer(modifier = Modifier.height(3.dp))
            data.country.map(){
                Text(text = "- ${convertCountryCodeToFullName(it.country_id)} - ${String.Companion.format("%.2f", (it.probability * 100))}%", modifier = Modifier.padding(horizontal = 25.dp))
            }
            if(!lastElement){
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(top = 15.dp))
            }
        }
}
@Preview
@Composable
fun NameDataCardPreview(){

}