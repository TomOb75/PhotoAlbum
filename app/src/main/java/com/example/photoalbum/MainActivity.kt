package com.example.photoalbum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photoalbum.ui.theme.PhotoAlbumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoAlbumTheme {
                SlideShow()

            }
        }
    }
}

@Composable
fun SlideShow(modifier: Modifier = Modifier) {
    var slideNumber by remember { mutableIntStateOf(1) }
    var inputText by remember { mutableStateOf("") }
    val imageResource = when(slideNumber){
        1 -> R.drawable.bus
        2 -> R.drawable.file
        3 -> R.drawable.house
        4 -> R.drawable.lake
        else -> R.drawable.peoples
    }
    val captionResource = when(slideNumber){
        1 -> "$slideNumber. This is a bus I saw"
        2 -> "$slideNumber. This is the 'worlds tallest' filing cabinet"
        3 -> "$slideNumber. This is a house on Fort Ticonderoga"
        4 -> "$slideNumber. This is the lake by Fort Ticonderoga"
        else -> "$slideNumber. This is a flag on Fort Ticonderoga"
    }
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = captionResource
        )
        Text(
            text = captionResource,
            fontSize = 16.sp,
            lineHeight = 20.sp
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Button(
                onClick = { slideNumber = if (slideNumber > 1) slideNumber-1 else 5}
            ) {
                Text(stringResource(R.string.back))
            }
            Button(
                onClick = {slideNumber = if (slideNumber < 5) slideNumber+1 else 1 }
            ) {
                Text(stringResource(R.string.next))
            }
        }


        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text(stringResource(R.string.image_search)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Button(
            onClick = {
                val number = inputText.toIntOrNull()
                if (number != null && number in 1..5) {
                    slideNumber = number
                    inputText = ""
                }
            },
            enabled = inputText.toIntOrNull() in 1..5
        ) {
            Text(stringResource(R.string.goToImage))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SlideShowPreview(){
    SlideShow()
}
