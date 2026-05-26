class TapeHead(private var position: UInt) {
    fun getPosition(): Int = position.toInt()

    fun move() {
        position += 1u
    }
}