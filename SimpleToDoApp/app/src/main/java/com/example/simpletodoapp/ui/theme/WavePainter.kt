package com.example.simpletodoapp.ui.theme

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import kotlin.math.PI
import kotlin.math.sin

class WavePainter(
    private val color1: Color,
    private val color2: Color,
    private val amplitude: Float,
    private val frequency: Float
) : Painter() {
    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, height)
            for (i in 0..width.toInt()) {
                val x = i.toFloat()
                val y = (amplitude * sin((x / width) * frequency * 2 * PI)).toFloat() + (height / 2)
                lineTo(x, y)
            }
            lineTo(width, height)
            close()
        }

        drawPath(
            path = path,
            brush = Brush.verticalGradient(
                colors = listOf(color1, color2)
            )
        )
    }
}