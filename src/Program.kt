import java.io.File

class Program(
    filePath: String,
    private val inputTape: InputTape,
    private val outputTape: OutputTape,
    private val memory: Memory
) {
    private val labels: ArrayList<Label> = arrayListOf()
    private val instructionTexts: ArrayList<String> = arrayListOf()

    init {
        val lines = File(filePath).readLines()
        collectLabels(lines)
        collectInstructionText(lines)
    }

    private fun collectLabels(lines: List<String>) {
        var instructionIndex = 0
        for (line in lines) {
            if (hasLabel(line)) {
                labels.add(Label(getLabel(line), instructionIndex))
            }
            instructionIndex += 1
        }
    }

    private fun hasLabel(line: String): Boolean {
        for (char in line) {
            if (char == ':') {
                return true
            }
        }
        return false
    }

    private fun getLabel(line: String): String {
        var label = ""
        var i = 0
        while (line[i] != ':') {
            label += line[i]
            i += 1
        }
        return label.trim()
    }

    private fun collectInstructionText(lines: List<String>) {
        for (line in lines) {
            instructionTexts.add(ignoreLabel(line))
        }
    }

    private fun ignoreLabel(line: String) = line.substringAfter(":").trim()

    fun getInstructionCount(): Int {
        return instructionTexts.size
    }

    fun collectInstructions(programCounter: ProgramCounter, halt: Halt): List<Instruction> {
        val instructions = (0..<getInstructionCount())
            .asSequence()
            .map { index -> getInstructionText(index) }
            .map { instructionText -> split(instructionText) }
            .map { command -> parseInstruction(command, programCounter, halt) }
            .toList()
        return instructions
    }

    private fun getInstructionText(index: Int): String {
        return instructionTexts[index]
    }

    private fun split(instructionText: String): List<String> {
        val splitInstruction = instructionText.trim().split(" ")
        if (splitInstruction.size !in 1..2) {
            throw IllegalArgumentException("Instruction must have one or two parts.")
        }
        return splitInstruction
    }

    private fun parseInstruction(command: List<String>, programCounter: ProgramCounter, halt: Halt): Instruction {
        return when (command[0].uppercase()) {
            "LOAD" -> Load(parseOperand(command[1]), memory, programCounter)
            "STORE" -> Store(parseRegisterMarkerOperand(command[1]), memory, programCounter)

            "ADD" -> Add(parseOperand(command[1]), memory, programCounter)
            "SUB" -> Sub(parseOperand(command[1]), memory, programCounter)
            "MULT" -> Mult(parseOperand(command[1]), memory, programCounter)
            "DIV" -> Div(parseOperand(command[1]), memory, programCounter)

            "READ" -> Read(command[1].toInt(), memory, inputTape, programCounter)
            "WRITE" -> Write(parseOperand(command[1]), memory, outputTape, programCounter)

            "JUMP" -> Jump(getIndex(command[1]), programCounter)
            "JZERO" -> JZero(getIndex(command[1]), memory, programCounter)
            "JGTZ" -> JGTZ(getIndex(command[1]), memory, programCounter)

            "HALT" -> halt

            else -> throw IllegalArgumentException("Unknown instruction: ${command[0]}")
        }
    }

    private fun parseOperand(operandText: String): Operand {
        if (operandText.startsWith("=")) return Immediate(getOperandValue(operandText, 1))
        return parseRegisterMarkerOperand(operandText)
    }

    private fun parseRegisterMarkerOperand(operandText: String): RegisterMarkerOperand {
        if (operandText.startsWith("*")) return Indirect(getOperandValue(operandText, 1))
        return Direct(getOperandValue(operandText, 0))
    }

    private fun getOperandValue(operandText: String, startIndex: Int) = operandText.substring(startIndex).toInt()

    private fun getIndex(labelName: String): Int {
        for (label in labels) {
            val index = label.getIndexIfMatches(labelName)
            if (index != null) return index
        }
        throw IllegalArgumentException("No such label found: $labelName!")
    }
}