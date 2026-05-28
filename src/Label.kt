class Label(private val text: String, private val index: Int) {
    fun matches(labelName: String) = text == labelName
    fun getIndexIfMatches(labelName: String) = if (matches(labelName)) index else null
}