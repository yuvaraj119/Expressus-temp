package com.expressus.compose.components.leftPanel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Slot
import com.expressus.compose.themes.CoffeeSlotTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun CoffeeSlot(
    modifier: Modifier,
    isGrinding: Boolean = false,
    isPouring: Boolean = false,
    pouringSpeed: Long,
    slotOffset: Dp,
    faucetOffsets: FaucetOffsets,
) {
    var cupVrtPadding: Dp by remember { mutableStateOf(0.dp) }
    var cupHrzPadding: Dp by remember { mutableStateOf(0.dp) }

    LaunchedEffect(isGrinding) {
        yield()
        cupVrtPadding = 0.dp
        cupHrzPadding = 0.dp
        while (isGrinding) {
            cupVrtPadding = (-1..1).random().dp
            cupHrzPadding = (0..1).random().dp
            delay(50)
        }
    }

    CoffeeSlotTheme {
        BoxWithConstraints(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Slot(
                modifier = Modifier.height(maxHeight).fillMaxWidth(),
                strokeWidth = 30.dp,
                topOffset = slotOffset,
                convexTop = false,
                convexBottom = true,
                flatBottom = true,
                top = MaterialTheme.colorScheme.primary,
                start = MaterialTheme.colorScheme.primary,
                end = MaterialTheme.colorScheme.primary,
                bottom = MaterialTheme.colorScheme.primaryContainer,
                background = Brush.verticalGradient(listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary))
            )
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                CoffeeStream(
                    modifier = Modifier
                        .width(this@BoxWithConstraints.maxWidth / 20)
                        .fillMaxHeight()
                        .padding(vertical = 10.dp),
                    speed = pouringSpeed,
                    pouring = isPouring
                )
                CoffeeFaucet(Modifier.padding(horizontal = (this@BoxWithConstraints.maxWidth.value / 2.7).dp), faucetOffsets)
            }
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp + cupVrtPadding, start = cupHrzPadding)
            ) {
                val cupHeight = remember(this@BoxWithConstraints.maxWidth) { this@BoxWithConstraints.maxWidth / 3 }
                val shadowHeight = remember(cupHeight) { (cupHeight + 5.dp) / 10 }

                //shadow
                Canvas(Modifier.size(cupHeight)) {
                    drawOval(
                        color = Color.Black.copy(0.2f),
                        topLeft = Offset(0f, this.size.height - shadowHeight.value / 2),
                        size = Size(size.width, shadowHeight.value)
                    )
                }
                Cup(cupHeight)
            }

            //Overlay
            Slot(
                modifier = Modifier.height(maxHeight).fillMaxWidth(),
                strokeWidth = 30.dp,
                topOffset = slotOffset,
                convexTop = false,
                convexBottom = true,
                flatBottom = true,
                top = Color.Transparent,
                bottom = Color.Transparent,
                start = MaterialTheme.colorScheme.primary,
                end = MaterialTheme.colorScheme.primary,
                background = Brush.horizontalGradient(
                    0.0f to Color.Black.copy(0.85f),
                    0.4f to Color.Transparent,
                    0.5f to Color.Transparent,
                    1.0f to Color.Black.copy(0.85f)
                )
            )
        }
    }
}