
fun <T> Array<T>.rev() {
	val midPoint = size / 2
	var reverseIndex = lastIndex

	if (midPoint < 0) return

	for (index in 0..midPoint) {
		val tmp = this[index]
		this[index] = this[reverseIndex]
		this[reverseIndex] = tmp
		reverseIndex--
	}
}