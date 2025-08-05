package com.illanes.myapplication

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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.illanes.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                App(modifier = Modifier)
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Hand(
                    modifier = Modifier
                        .size(100.dp),
                    handOption = HandOption.PAPER
                )
                Hand(
                    modifier = Modifier
                        .size(100.dp),
                    handOption = HandOption.ROCK
                )
                Hand(
                    modifier = Modifier
                        .size(100.dp),
                    handOption = HandOption.SCISSORS
                )
            }
        }
    }
}

@Composable
fun Hand(
    modifier: Modifier = Modifier,
    handOption: HandOption = HandOption.PAPER
) {
    val handIcon = when(handOption) {
        HandOption.PAPER -> R.drawable.paper
        HandOption.ROCK -> R.drawable.rock
        HandOption.SCISSORS -> R.drawable.scissors
    }

    Image(
        painter = painterResource(id = handIcon),
        contentDescription = stringResource(id = R.string.rock),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    MyApplicationTheme {
        App()
    }
}