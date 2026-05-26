class InputTape(private val cells: List<Cell>, private val tapeHead: TapeHead) : Tape {
    fun read(): Int {
        val cell = cells[tapeHead.getPosition()]
        tapeHead.move()
        return cell.value
    }

    fun hasNextCell(): Boolean {
        return tapeHead.getPosition() < cells.size
    }
}