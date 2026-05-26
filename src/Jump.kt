class Jump(private val targetIndex: Int, private val programCounter: ProgramCounter) : Instruction {
    override fun execute() {
        programCounter.jump(targetIndex)
    }
}