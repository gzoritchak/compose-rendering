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
                when (renderingWith) {
                    RenderingWith.Square -> drawRect(
                        color = Colors.Web.aliceblue.toColor(),
                        Offset(
                            xScale(particle.position.x).toFloat(),
                            yScale(particle.position.y).toFloat(),
                        ),
                        Size(10f, 10f)
                    )

                    RenderingWith.Circle -> drawCircle(
                        color = Colors.Web.aliceblue.toColor(),
                        radius = 5f,
                        center = Offset(
                            xScale(particle.position.x).toFloat(),
                            yScale(particle.position.y).toFloat(),
                        )
                    )

                    RenderingWith.Diamond -> drawPath(Path().apply {
                        moveTo(xScale(particle.position.x).toFloat(), yScale(particle.position.y).toFloat() + 5)
                        lineTo(xScale(particle.position.x).toFloat() + 5, yScale(particle.position.y).toFloat())
                        lineTo(xScale(particle.position.x).toFloat(), yScale(particle.position.y).toFloat() - 5)
                        lineTo(xScale(particle.position.x).toFloat() - 5, yScale(particle.position.y).toFloat())
                        close()
                    }, Colors.Web.aliceblue.toColor())

                }

            }
        }

    }
}