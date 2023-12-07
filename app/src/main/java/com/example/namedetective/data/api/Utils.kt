package com.example.namedetective.data.api
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import java.util.Locale


fun convertCountryCodeToFullName (code: String): String {
    return Locale("", code).getDisplayCountry(Locale("EN"))
}

fun convertSecondaryCountryCodesToFullNames(data: Array<CountryData>): String {

    var txt: String = ""
    data.forEachIndexed { idx, value ->
        if (idx >= 1) {
            txt += if (idx == 1) {
                "${convertCountryCodeToFullName(value.country_id)}"
            } else {
                ", ${convertCountryCodeToFullName(value.country_id)}"
            }
        }
    }
    return txt
}

fun convertNameDataToReadable (data: NameData): AnnotatedString {
    val txt = buildAnnotatedString {
        append("We have analyzed over ${data.count.toInt()} results in order to determine the home country of ${data.name}. With the probability of ${String.Companion.format("%.2f", (data.country[0].probability * 100))}% it comes from ")
        withStyle(
            SpanStyle(
               fontWeight = FontWeight.Bold
            )
        ) {
            append("${convertCountryCodeToFullName(data.country[0].country_id)}")
        }
        append(". Other possibilites are: ${convertSecondaryCountryCodesToFullNames(data.country)}.")
    }

    return txt
}

