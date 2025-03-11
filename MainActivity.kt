package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            when (currentStep) {
                1 -> {
                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.lemon_tree_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { currentStep = 2 }
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Space between image and text
                    Text(text = stringResource(R.string.lemon_tree))
                }

                2 -> {
                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.lemon_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                squeezeCount++
                                if (squeezeCount >= 5) {
                                currentStep = 3 }}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "${stringResource(R.string.squeeze_lemon)} ($squeezeCount/5)")
                }

                3 -> {
                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.drink_lemonade_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { currentStep = 4 }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = stringResource(R.string.drink_lemonade))
                }

                4 -> {
                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.lemon_restart_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { currentStep = 1
                                        squeezeCount = 0
                            }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = stringResource(R.string.restart_game))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}
