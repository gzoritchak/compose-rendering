import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import io.data2viz.color.Colors
import io.data2viz.scale.Scales

@Composable
fun ParticlesCanvas(time: Long, renderingWith: RenderingWith, partCount: Int, ) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        val xScale = Scales.Continuous.linear {
            domain = listOf(.0, 5.0)
            range = listOf(this@Canvas.size.width / 2.0, this@Canvas.size.width.toDouble())
        }
        val yScale = Scales.Continuous.linear {
            domain = listOf(.0, 5.0)
            range = listOf(this@Canvas.size.height / 2.0, this@Canvas.size.height.toDouble())
        }

        drawIntoCanvas {

            val fake = time //without this line the rendering is not done for all frames

            drawRect(red)
            (0 until partCount).forEach { id ->
                val particle = particles[id]
                val x = xScale(particle.x).toFloat()
                val y = yScale(particle.y).toFloat()
                val color = Colors.Web.aliceblue.toColor()
                when (renderingWith) {
                    RenderingWith.Square -> drawRect(
                        color = color,
                        Offset(x, y),
                        Size(10f, 10f)
                    )
                    RenderingWith.Circle -> drawCircle(
                        color = color,
                        radius = 5f,
                        center = Offset(x, y)
                    )
                    RenderingWith.Diamond -> drawPath(Path().apply {
                        moveTo(x, y + 5)
                        lineTo(x + 5, y)
                        lineTo(x, y - 5)
                        lineTo(x - 5, y)
                        close()
                    }, color)
                }
            }
        }

    }
}