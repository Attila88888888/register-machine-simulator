class Write(
    private val operand: Operand,
    private val memory: Memory,
    private val outputTape: OutputTape,
    private val programCounter: ProgramCounter
) : Instruction {
    override fun execute() {
        val value = operand.resolve(memory)
        outputTape.write(value)
        programCounter.increment()
    }
}