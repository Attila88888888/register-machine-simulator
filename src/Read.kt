class Read(
    private val register: Int,
    private val memory: Memory,
    private val inputTape: InputTape,
    private val programCounter: ProgramCounter
) : Instruction {
    override fun execute() {
        if (inputTape.hasNextCell()) {
            memory.setValue(register, inputTape.read())
        } else {
            throw IllegalArgumentException("No more input cell")
        }
        programCounter.increment()
    }
}