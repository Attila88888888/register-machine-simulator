class Immediate(private val value: Int) : Operand {
    override fun resolve(memory: Memory): Int {
        return value
    }
}