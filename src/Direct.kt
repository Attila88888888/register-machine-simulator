class Direct(private val value: Int) : RegisterMarkerOperand {
    override fun resolve(memory: Memory): Int {
        return memory.getContent(value)
    }

    override fun resolveDestination(memory: Memory): Int = value
}