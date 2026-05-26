class Load(
    private val operand: Operand,
    private val memory: Memory,
    private val programCounter: ProgramCounter
) : Instruction {
    override fun execute() {
        memory.setValue(0, operand.resolve(memory))
        programCounter.increment()
    }
}