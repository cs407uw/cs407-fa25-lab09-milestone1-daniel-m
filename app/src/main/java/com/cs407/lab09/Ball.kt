package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        val multiplier = 50f
        val scaledX = xAcc * multiplier
        val scaledY = yAcc * multiplier

        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = scaledX
            accY = scaledY
            return
        }

        val accelPrevX = accX
        val accelPrevY = accY

        accX = scaledX
        accY = scaledY

        val accelCurrX = accX
        val accelCurrY = accY

        val deltaTime = dT

        val velocityPrevX = velocityX
        val velocityPrevY = velocityY

        velocityX = velocityPrevX + 0.5f * (accelPrevX + accelCurrX) * deltaTime
        velocityY = velocityPrevY + 0.5f * (accelPrevY + accelCurrY) * deltaTime

        val deltaX = velocityPrevX * deltaTime + (deltaTime * deltaTime) * (3f * accelPrevX + accelCurrX) / 6f
        val deltaY = velocityPrevY * deltaTime + (deltaTime * deltaTime) * (3f * accelPrevY + accelCurrY) / 6f

        posX += deltaX
        posY += deltaY
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        if (posX < 0f) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }

        if (posX + ballSize > backgroundWidth) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }

        if (posY < 0f) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }

        if (posY + ballSize > backgroundHeight) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f

        velocityX = 0f
        velocityY = 0f

        accX = 0f
        accY = 0f

        isFirstUpdate = true
    }
}