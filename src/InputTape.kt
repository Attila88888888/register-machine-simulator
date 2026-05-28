class InputTape(private val cells: List<Cell>, private val tapeHead: TapeHead) {
    fun read(): Int {
        val cell = cells[tapeHead.position]
        tapeHead.move()
        return cell.value
    }

    fun hasNextCell(): Boolean {
        return tapeHead.position < cells.size
    }
}