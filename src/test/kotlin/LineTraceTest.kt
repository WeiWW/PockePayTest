import org.junit.Test
import kotlin.test.assertTrue

class LineTraceTest {
	@Test
	fun `123123`(){
		val input = "123123"
		val expect = "ABCADEFDBECF"
		val result = trace(input)
		assertTrue { result == expect }
	}

	@Test
	fun `123212`(){
		val input = "123212"
		val expect = "ABCADBEFCEDF"
		val result = trace(input)
		assertTrue { result == expect }
	}

	@Test
	fun `111111`(){
		val input = "111111"
		val expect = "ABEF"
		val result = trace(input)
		assertTrue { result == expect }
	}

	@Test
	fun `222222`(){
		val input = "222222"
		val expect = "ACFEDF"
		val result = trace(input)
		assertTrue { result == expect }
	}

	@Test
	fun `333333`(){
		val input = "333333"
		val expect = "ADFECF"
		val result = trace(input)
		assertTrue { result == expect }
	}
}