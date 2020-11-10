import org.junit.Assert
import org.junit.Test

class FlattenTest {
	private val nestedMultiDArray = arrayOf(1, 2, arrayOf(3, 4, listOf(5), arrayOf<Array<Array<Any>>>(), 6, 7), setOf(8,10), 9,
		mapOf(1 to listOf('c','a'), 2 to listOf('b')))
	private val expectedFlattenArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8,10, 9,1,'c','a',2,'b')

	@Test
	fun `flatten an array using recursion and iterators`(){
		val result = mutableListOf<Any>()
		nestedMultiDArray.flattenTo(result)
		println(result)
		Assert.assertArrayEquals(expectedFlattenArray,result.toTypedArray())
	}

	@Test
	fun `flatten an array using recursion only`(){
		val result = mutableListOf<Any>()
		nestedMultiDArray.flattenRTo(result)
		println(result)
		Assert.assertArrayEquals(expectedFlattenArray,result.toTypedArray())
	}

	@Test
	fun `flatten an array using iterators only`(){
		val result = mutableListOf<Any>()
		nestedMultiDArray.flattenITo(result)
		println(result)
		Assert.assertArrayEquals(expectedFlattenArray,result.toTypedArray())
	}
}