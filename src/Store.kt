class Store(
    private val registerMarker: RegisterMarkerOperand,
    private val memory: Memory,
    private val programCounter: ProgramCounter
) : Instruction {
    override fun execute() {
        memory.setValue(registerMarker.resolveDestination(memory), memory.getContent(0))
        programCounter.increment()
    }
}