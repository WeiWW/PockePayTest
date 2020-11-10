/*
*   Flatten nested multidimensional array using recursion and iterators.
*/
fun <T, C : MutableCollection<in Any>> Array<T>.flattenTo(destination: C): C {

	for (element in this) {
		when (element) {
			is Array<*> -> {
				element.flattenTo(destination)
			}
			is Collection<*> -> {
				element.toTypedArray().flattenTo(destination)
			}
			is Map<*, *> -> {
				val flatten = (element as Map<*, *>).flatten()
				flatten.toTypedArray().flattenTo(destination)
			}
			else -> {
				element?.let {
					destination.add(it)
				}
			}
		}
	}

	return destination
}

/*
*   Flatten nested multidimensional array only using recursion.
*/
fun <T, C : MutableCollection<in Any>> Array<T>.flattenRTo(destination: C): C {
	if (isEmpty()) return destination

	first().let {
		when (it) {
			is Array<*> -> {
				it.flattenRTo(destination)
			}
			is Collection<*> -> {
				it.toTypedArray().flattenRTo(destination)
			}
			is Map<*, *> -> {
				val flatten = (it as Map<*, *>).flatten()
				flatten.toTypedArray().flattenTo(destination)
			}
			else -> {
				it?.let { element -> destination.add(element) }
			}
		}
	}

	(drop(1) as List<*>).toTypedArray().flattenRTo(destination)

	return destination
}

/*
*   Flatten nested multidimensional array only using iterators.
*/
fun <T, C : MutableCollection<Any>> Array<T>.flattenITo(destination: C): C {
	if (isEmpty()) return destination

	val elementList = arrayListOf<Any?>()
	toCollection(ArrayList()).let { elementList.addAll(it) }
	elementList.reverse()

	while (elementList.isNotEmpty()) {
		val element = elementList.last()
		elementList.removeAt(elementList.lastIndex)
		element?.let { it ->
			when (it) {
				is Array<*> -> {
					elementList.addAll(it.reversed())
				}
				is Collection<*> -> {
					elementList.addAll(it.reversed())
				}
				is Map<*, *> -> {
					val flatten = it.flatten()
					elementList.addAll(flatten.reversed())
				}
				else -> {
					destination.add(it)
				}
			}
		}
	}
	return destination
}

private fun <V> Map<*, V>.flatten(): List<Any?> {
	return entries.flatMap {
		val list = mutableListOf(it.key)

		if (it.value is Collection<*>) {
			list.addAll(it.value as Collection<*>)
		} else {
			list.add(it.value)
		}

		list
	}
}