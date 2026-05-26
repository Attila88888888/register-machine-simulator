class Mult(
    private val operand: Operand,
    private val memory: Memory,
    private val programCounter: ProgramCounter
) : Instruction {
    override fun execute() {
        memory.setValue(0, memory.getContent(0) * operand.resolve(memory))
        programCounter.increment()
    }
}