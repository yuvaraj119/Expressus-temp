package com.expressus.compose.components.rightPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.expressus.compose.components.Slot
import com.expressus.compose.themes.PaymentSocketTheme

@Composable
fun PaymentSocket(modifier: Modifier) {
    PaymentSocketTheme {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PlasticTile(
                Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .graphicsLayer {
                        shape = RoundedCornerShape(4.dp)
                        clip = true
                    }
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.secondary
                            )
                        )
                    ),
                shape = RoundedCornerShape(4.dp),
                withGlossy = false
            )
            Spacer(Modifier.size(6.dp))
            Slot(
                modifier = Modifier.fillMaxWidth().height(15.dp),
                strokeWidth = 6.dp,
                top = Color.DarkGray,
                start = Color.DarkGray,
                end = Color.DarkGray,
                bottom = Color.Gray,
                background = Brush.linearGradient(colors = listOf(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background))
            )
        }
    }
}