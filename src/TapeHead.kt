class TapeHead(private var value: UInt) {
    val position get() = value.toInt()

    fun move() {
        value += 1u
    }
}