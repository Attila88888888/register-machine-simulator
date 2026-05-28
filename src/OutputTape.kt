class OutputTape(private val cells: ArrayList<Cell>, private val tapeHead: TapeHead, private val encoding: Encoding) {
    fun write(value: Int) {
        cells.add(Cell(value))
        tapeHead.move()
    }

    fun show() {
        for (cell in cells) {
            val char = encoding.decode(cell.value)
            if (char != null) {
                print(char)
            } else {
                print(cell.value)
            }
        }
    }
}