import java.io.File

class Program(filePath: String) {
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
        return label
    }

    private fun collectInstructionText(lines: List<String>) {
        for (line in lines) {
            instructionTexts.add(ignoreLabel(line))
        }
    }

    private fun ignoreLabel(line: String) = line.substringAfter(":").trim()

    fun getInstructionText(index: Int): String {
        return instructionTexts[index]
    }

    fun getInstructionCount(): Int {
        return instructionTexts.size
    }

    fun getIndex(labelName: String): Int {
        for (label in labels) {
            if (label.matches(labelName)) return label.getIndex()
        }
        throw IllegalArgumentException("No such label found: $labelName!")
    }
}