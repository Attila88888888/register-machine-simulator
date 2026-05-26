interface RegisterMarkerOperand : Operand {
    fun resolveDestination(memory: Memory): Int
}