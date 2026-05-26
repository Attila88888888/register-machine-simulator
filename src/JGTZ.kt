class JGTZ(private val targetIndex: Int, private val memory: Memory, private val programCounter: ProgramCounter) :
    Instruction {
    override fun execute() {
        if (memory.getContent(0) > 0) {
            programCounter.jump(targetIndex)
        } else {
            programCounter.increment()
        }
    }
}