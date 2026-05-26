class Div(
    private val operand: Operand,
    private val memory: Memory,
    private val programCounter: ProgramCounter
) : Instruction {
    override fun execute() {
        val divisor = operand.resolve(memory)
        if (divisor == 0) throw IllegalArgumentException("Division by zero is illegal!")
        memory.setValue(0, memory.getContent(0) / divisor)
        programCounter.increment()
    }
}