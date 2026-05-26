class Indirect(private val value: Int) : RegisterMarkerOperand {
    override fun resolve(memory: Memory): Int {
        val pointer = memory.getContent(value)
        return memory.getContent(pointer)
    }

    override fun resolveDestination(memory: Memory): Int = memory.getContent(value)
}