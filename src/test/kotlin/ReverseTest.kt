import org.junit.Assert
import org.junit.Test

class ReverseTest {
	@Test
	fun `reverse array`(){
		val inputArray = arrayOf(1,2,3,4,5,6,7,8,9)
		val expectArray = arrayOf(9,8,7,6,5,4,3,2,1)

		inputArray.rev()

		Assert.assertArrayEquals(expectArray,inputArray)
	}
}