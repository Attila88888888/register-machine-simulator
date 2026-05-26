class Encoding {
    private val charToInt: ArrayList<Pair<Char, Int>> = arrayListOf()
    private var counter = 1

    fun encode(input: String): List<Cell> {
        val cells = ArrayList<Cell>()
        for (char in input) {
            if (!isEncoded(char)) {
                charToInt.add(Pair(char, counter))
                counter += 1
            }
            cells.add(Cell(getValue(char)))
        }
        return cells
    }

    private fun isEncoded(char: Char): Boolean {
        for (pair in charToInt) {
            if (pair.first == char) return true
        }
        return false
    }

    private fun getValue(char: Char): Int {
        for (pair in charToInt) {
            if (pair.first == char) return pair.second
        }
        throw IllegalStateException("Character not encoded: $char")
    }

    fun decode(value: Int): Char? {
        for (pair in charToInt) {
            if (pair.second == value) return pair.first
        }
        return null
    }
}