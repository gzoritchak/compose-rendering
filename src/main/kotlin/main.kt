import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import io.data2viz.color.Color as D2VColor


const val appWidth = 1280
const val appHeight = 720


enum class RenderingWith {
    Square, Circle, Diamond
}

val particlesCount = listOf(100, 300, 1000, 3000, 10_000, 30_000, 100_000)

val particles = Array(particlesCount.last()) { RandomParticle() }


val red = Color(red = 180, green = 0, blue = 0)

fun D2VColor.toColor() = Color(this.r, this.g, this.b)

fun main() = Window(
    title = "Particles Compose Demo",
    size = IntSize(appWidth, appHeight)
) {

    var lastUpdate by remember { mutableStateOf(0L) }
    var time by remember { mutableStateOf(0L) }

    var renderingWith by remember { mutableStateOf(RenderingWith.Square) }

    var particleCount by remember { mutableStateOf(particlesCount.first()) }

    var fpsCount by remember { mutableStateOf(0) }
    var fps by remember { mutableStateOf(0)}


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            RenderingWith.values().forEach {
                Button(
                    onClick = {renderingWith = it}
                ) {
                    Text(it.name)
                }
            }
            Box(
                modifier = Modifier.padding(10.dp),
            ) {
                Text("Rendering $particleCount ${renderingWith.name}s at $fps FPS")
            }

        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            particlesCount.forEach {
                Button(
                    onClick = {particleCount = it}
                ) {
                    Text(it.toString())
                }
            }
        }

        ParticlesCanvas(time, renderingWith, particleCount)
    }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameMillis { ms ->
                (0 until particleCount).forEach { id ->
                    particles[id].updatePositionAndSpeed()
                }
                time = ms
                fpsCount++
                if (fpsCount == 10) {
                    fps = (10000 / (ms - lastUpdate)).toInt()
                    lastUpdate = ms
                    fpsCount = 0
                }
            }
        }
    }
}





