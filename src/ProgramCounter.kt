class ProgramCounter(private var currentValue: UInt, private val instructionCount: Int) {
    val value get() = currentValue.toInt()

    fun increment() {
        val nextValue = currentValue + 1u
        if (isInInstructions(nextValue.toInt())) {
            currentValue = nextValue
        } else {
            throw IllegalArgumentException("Cannot jump to position $nextValue")
        }
    }

    fun jump(position: Int) {
        if (isInInstructions(position)) {
            currentValue = position.toUInt()
        } else {
            throw IllegalArgumentException("Cannot jump to position $position")
        }
    }

    private fun isInInstructions(position: Int) = position in 0..<instructionCount
}