class Write(
    private val operand: Operand,
    private val memory: Memory,
    private val outputTape: OutputTape,
    private val encoding: Encoding,
    private val programCounter: ProgramCounter
) : Instruction {
    override fun execute() {
        val value = operand.resolve(memory)
        outputTape.write(value)
        if (operand is Immediate) {
            print(value)
        } else {
            val char = encoding.decode(value)
            if (char != null) {
                print(char)
            } else {
                print(value)
            }
        }
        programCounter.increment()
    }
}