class Halt(private val stop: () -> Unit) : Instruction {
    override fun execute() {
        stop()
    }
}