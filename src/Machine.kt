class Machine(
    filePath: String,
    private val inputTape: InputTape,
    private val outputTape: OutputTape,
    private val encoding: Encoding
) {
    private var running = false

    private val memory = Memory()
    private val program = Program(filePath)
    private val programCounter = ProgramCounter(0u, program.getInstructionCount())

    fun run() {
        running = true
        val instructions = collectInstructions()

        while (running) {
            val instruction = instructions[programCounter.getValue()]
            instruction.execute()
        }
        println()
    }

    private fun collectInstructions(): List<Instruction> {
        val instructions = (0..<program.getInstructionCount())
            .asSequence()
            .map { index -> program.getInstructionText(index) }
            .map { instructionText -> split(instructionText) }
            .map { command -> parseInstruction(command) }
            .toList()
        return instructions
    }

    private fun parseInstruction(command: List<String>): Instruction {
        return when (command[0].uppercase()) {
            "LOAD" -> Load(parseOperand(command[1]), memory, programCounter)
            "STORE" -> Store(parseRegisterMarkerOperand(command[1]), memory, programCounter)

            "ADD" -> Add(parseOperand(command[1]), memory, programCounter)
            "SUB" -> Sub(parseOperand(command[1]), memory, programCounter)
            "MULT" -> Mult(parseOperand(command[1]), memory, programCounter)
            "DIV" -> Div(parseOperand(command[1]), memory, programCounter)

            "READ" -> Read(command[1].toInt(), memory, inputTape, programCounter)
            "WRITE" -> Write(parseOperand(command[1]), memory, outputTape, encoding, programCounter)

            "JUMP" -> Jump(program.getIndex(command[1]), programCounter)
            "JZERO" -> JZero(program.getIndex(command[1]), memory, programCounter)
            "JGTZ" -> JGTZ(program.getIndex(command[1]), memory, programCounter)

            "HALT" -> Halt { running = false }

            else -> throw IllegalArgumentException("Unknown instruction: ${command[0]}")
        }
    }

    private fun split(instructionText: String) = instructionText.split(" ")

    private fun parseOperand(operandText: String): Operand {
        if (operandText.startsWith("=")) return Immediate(getOperandValue(operandText, 1))
        return parseRegisterMarkerOperand(operandText)
    }

    private fun parseRegisterMarkerOperand(operandText: String): RegisterMarkerOperand {
        if (operandText.startsWith("*")) return Indirect(getOperandValue(operandText, 1))
        return Direct(getOperandValue(operandText, 0))
    }

    private fun getOperandValue(operandText: String, startIndex: Int) = operandText.substring(startIndex).toInt()
}