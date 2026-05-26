class Memory {
    private val registers = arrayListOf(Register(0))

    fun getContent(index: Int): Int {
        ensureCapacity(index)
        return registers[index].value
    }

    fun setValue(index: Int, value: Int) {
        ensureCapacity(index)
        registers[index].value = value
    }

    private fun ensureCapacity(index: Int) {
        while (isOutOfBound(index)) {
            addRegister()
        }
    }

    private fun isOutOfBound(index: Int): Boolean {
        return registers.size <= index
    }

    private fun addRegister() {
        registers.add(Register(0))
    }
}