import io.data2viz.geom.Point

/**
 * A particle with a 2D position that will move randomly beetween approximatively -5,-5 and +5,+5
 */
class RandomParticle {

    var position = Point()
    var speed = Point()

    fun updatePositionAndSpeed() {
        position += speed
        speed += Point(
            .04 * (Math.random() - .5) - .05 * speed.x - .0005 * position.x,
            .04 * (Math.random() - .5) - .05 * speed.y - .0005 * position.y
        )
    }
}