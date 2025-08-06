package com.illanes.myapplication

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun Player(
    modifier: Modifier = Modifier,
    title: Int,
    handOption: HandOption = HandOption.PAPER,
    isCountingDown: MutableState<Boolean>,
    score: MutableIntState
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

    Text(
        text = stringResource(id = title),
        fontSize = 20.sp,
        modifier = Modifier
    )
    Image(
        painter = if (isCountingDown.value) {
            painterResource(id = R.drawable.question)
        } else {
            painterResource(id = handIcon)
        },
        contentDescription = stringResource(id = R.string.rock),
        modifier = modifier
    )
    Text(
        text = if (isCountingDown.value) {
            "?"
        } else {
            stringResource(id = handText)
        },
        fontSize = 20.sp,
        modifier = Modifier
    )
    Text(
        text = if (isCountingDown.value) {
            stringResource(id = R.string.score) + ": ?"
        } else {
            stringResource(id = R.string.score) + ": " + score.intValue
        },
        fontSize = 20.sp,
        modifier = Modifier
    )
}