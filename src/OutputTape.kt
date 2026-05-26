class OutputTape(private val cells: ArrayList<Cell>, private val tapeHead: TapeHead) : Tape {
    fun write(value: Int) {
        cells.add(Cell(value))
        tapeHead.move()
    }
}