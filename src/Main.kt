fun main(args: Array<String>) {
    val filePath = args[0]
    val inputString =
        if (args.size > 1) {
            args[1]
        } else {
            print("Add the input word here: ")
            readln()
        }

    val encoding = Encoding()
    val inputTape = InputTape(encoding.encode(inputString), TapeHead(0u))
    val outputTape = OutputTape(arrayListOf(), TapeHead(0u))

    val machine = Machine(filePath, inputTape, outputTape, encoding)

    machine.run()
}