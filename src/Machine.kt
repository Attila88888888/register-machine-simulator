class Machine(filePath: String, private val inputTape: InputTape, private val outputTape: OutputTape) {
    private var running = false

    private val memory = Memory()
    private val program = Program(filePath, inputTape, outputTape, memory)
    private val programCounter = ProgramCounter(0u, program.getInstructionCount())

    fun run() {
        running = true
        val halt = Halt { running = false }
        val instructions = program.collectInstructions(programCounter, halt)

        while (running) {
            val instruction = instructions[programCounter.value]
            instruction.execute()
        }
        outputTape.show()
    }
}