class ProgramCounter(private var value: UInt, private val instructionCount: Int) {
    fun getValue() = value.toInt()

    fun increment() {
        val nextValue = value + 1u
        if (isInInstructions(nextValue.toInt())) {
            value = nextValue
        } else {
            throw IllegalArgumentException("Cannot jump to position $nextValue")
        }
    }

    fun jump(position: Int) {
        if (isInInstructions(position)) {
            value = position.toUInt()
        } else {
            throw IllegalArgumentException("Cannot jump to position $position")
        }
    }

    private fun isInInstructions(position: Int) = position in 0..<instructionCount
}