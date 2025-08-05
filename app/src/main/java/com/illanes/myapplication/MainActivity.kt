package com.illanes.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.illanes.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

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
    val computerHandOption = rememberSaveable { mutableStateOf(HandOption.PAPER) }
    val yourHandOption = rememberSaveable { mutableStateOf(HandOption.PAPER) }
    val gameResult = rememberSaveable { mutableIntStateOf(R.string.draw) }
    val computerScore = rememberSaveable { mutableIntStateOf(0) }
    val yourScore = rememberSaveable { mutableIntStateOf(0) }

    val isCountingDown = remember { mutableStateOf(false) }
    val counter = remember { mutableIntStateOf(3) }

    LaunchedEffect(isCountingDown.value) {
        if (isCountingDown.value) {
            while (counter.intValue > 0) {
                delay(1000)  // wait 1 second
                counter.intValue--
            }
            isCountingDown.value = false // Reset when done
        }
    }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.computers_choice),
                fontSize = 20.sp,
                modifier = Modifier
            )
            Hand(
                modifier = Modifier
                    .size(100.dp),
                handOption = computerHandOption.value
            )
            Text(
                text = stringResource(id = R.string.score) + ": " + computerScore.intValue,
                fontSize = 20.sp,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(thickness = 8.dp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (isCountingDown.value) {
                    counter.intValue.toString()
                } else {
                    stringResource(gameResult.intValue)
                },
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 8.dp)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.your_choice),
                fontSize = 20.sp,
                modifier = Modifier
            )
            Hand(
                modifier = Modifier
                    .size(100.dp),
                handOption = yourHandOption.value
            )
            Text(
                text = stringResource(id = R.string.score) + ": " + yourScore.intValue,
                fontSize = 20.sp,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        counter.intValue = 3
                        isCountingDown.value = true
                        yourHandOption.value = HandOption.PAPER
                        computerHandOption.value = HandOption.entries.random()
                        gameResult.intValue = getStatus(
                            yourHandOption.value,
                            computerHandOption.value
                        )
                        updateScore(gameResult.intValue, yourScore, computerScore)
                    },
                    Modifier.width(120.dp),
                    enabled = !isCountingDown.value
                ) {
                    Text(stringResource(R.string.paper))
                }
                Button(
                    onClick = {
                        counter.intValue = 3
                        isCountingDown.value = !isCountingDown.value
                        yourHandOption.value = HandOption.ROCK
                        computerHandOption.value = HandOption.entries.random()
                        gameResult.intValue = getStatus(
                            yourHandOption.value,
                            computerHandOption.value
                        )
                        updateScore(gameResult.intValue, yourScore, computerScore)
                    },
                    Modifier.width(120.dp),
                    enabled = !isCountingDown.value
                ) {
                    Text(stringResource(R.string.rock))
                }
                Button(
                    onClick = {
                        counter.intValue = 3
                        isCountingDown.value = !isCountingDown.value
                        yourHandOption.value = HandOption.SCISSORS
                        computerHandOption.value = HandOption.entries.random()
                        gameResult.intValue = getStatus(
                            yourHandOption.value,
                            computerHandOption.value
                        )
                        updateScore(gameResult.intValue, yourScore, computerScore)
                    },
                    Modifier.width(120.dp),
                    enabled = !isCountingDown.value
                ) {
                    Text(stringResource(R.string.scissors))
                }
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
    val handText = when(handOption) {
        HandOption.PAPER -> R.string.paper
        HandOption.ROCK -> R.string.rock
        HandOption.SCISSORS -> R.string.scissors
    }

    Image(
        painter = painterResource(id = handIcon),
        contentDescription = stringResource(id = R.string.rock),
        modifier = modifier
    )
    Text(
        text = stringResource(id = handText),
        fontSize = 20.sp,
        modifier = Modifier
    )
}

fun getStatus(yourHandOption: HandOption, computerHandOption: HandOption) : Int {
    val result = if (
        (yourHandOption == HandOption.PAPER && computerHandOption == HandOption.ROCK)
        || (yourHandOption == HandOption.ROCK && computerHandOption == HandOption.SCISSORS)
        || (yourHandOption == HandOption.SCISSORS && computerHandOption == HandOption.PAPER)
    ) {
        R.string.you_win
    } else if (
        (computerHandOption == HandOption.PAPER && yourHandOption == HandOption.ROCK)
        || (computerHandOption == HandOption.ROCK && yourHandOption == HandOption.SCISSORS)
        || (computerHandOption == HandOption.SCISSORS && yourHandOption == HandOption.PAPER)
    ) {
        R.string.you_lose
    } else {
        R.string.draw
    }
    return result
}

fun updateScore(state: Int, yourScore: MutableIntState, computerScore: MutableIntState) {
    if (state == R.string.you_win) {
        yourScore.intValue += 1
    } else if (state == R.string.you_lose) {
        computerScore.intValue += 1
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyApplicationTheme {
        App()
    }
}