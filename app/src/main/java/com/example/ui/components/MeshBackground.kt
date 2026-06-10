package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.MeshPink
import com.example.ui.theme.MeshPurple

@Composable
fun MeshBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                // Top Left Purple Mesh
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(MeshPurple, Color.Transparent),
                        center = Offset(size.width * 0.2f, size.height * 0.1f),
                        radius = size.width * 0.8f
                    ),
                    radius = size.width * 0.8f,
                    center = Offset(size.width * 0.2f, size.height * 0.1f)
                )

                // Bottom Right Pink Mesh
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(MeshPink, Color.Transparent),
                        center = Offset(size.width * 0.8f, size.height * 0.8f),
                        radius = size.width * 0.9f
                    ),
                    radius = size.width * 0.9f,
                    center = Offset(size.width * 0.8f, size.height * 0.8f)
                )
            }
    ) {
        content()
    }
}
