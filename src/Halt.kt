class Halt(private val halt: () -> Unit) : Instruction {
    override fun execute() {
        halt()
    }
}