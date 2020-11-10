private enum class Direction{ LEFT, RIGHT, UP, DOWN }

/*
* Use alphabet A~F to represent each side of cube.
* */
private enum class Cube(val toChar:Char){
	A('A'),
	B('B'),
	C('C'),
	D('D'),
	E('E'),
	F('F')}

/*
* The routes of Tile One, from [KEY] to [VALUE].
* */
private val tileOne = mapOf(
	Direction.LEFT to Direction.RIGHT,
	Direction.RIGHT to Direction.LEFT,
	Direction.UP to Direction.DOWN,
	Direction.DOWN to Direction.UP
)

/*
* The routes of Tile Two, from [KEY] to [VALUE].
* */
private val tileTwo = mapOf(
	Direction.LEFT to Direction.UP,
	Direction.UP to Direction.LEFT,
	Direction.DOWN to Direction.RIGHT,
	Direction.RIGHT to Direction.DOWN
)

/*
* The routes of Tile Three, from [KEY] to [VALUE].
* */
private val tileThree = mapOf(
	Direction.LEFT to Direction.DOWN,
	Direction.DOWN to Direction.LEFT,
	Direction.RIGHT to Direction.UP,
	Direction.UP to Direction.RIGHT
)

/*
*  Each num, range 1 to 3, correspond to a type of tile.
* */
private val charToTile = mapOf(
	'1' to tileOne,
	'2' to tileTwo,
	'3' to tileThree
)

private val cubeToPosition = mapOf(
	Cube.A to 0,
	Cube.B to 1,
	Cube.C to 2,
	Cube.D to 3,
	Cube.E to 4,
	Cube.F to 5
)

/*
* the adjacent side of side A of cube.
* */
private val aMap = mapOf(
	Direction.LEFT to Pair(Cube.F,Direction.RIGHT),
	Direction.RIGHT to Pair(Cube.B,Direction.LEFT),
	Direction.UP to Pair(Cube.C,Direction.LEFT),
	Direction.DOWN to Pair(Cube.D, Direction.LEFT)
)

/*
* the adjacent side of side B of cube.
* */
private val bMap = mapOf(
	Direction.LEFT to Pair(Cube.A, Direction.RIGHT),
	Direction.RIGHT to Pair(Cube.E,Direction.LEFT),
	Direction.UP to Pair(Cube.C,Direction.DOWN),
	Direction.DOWN to Pair(Cube.D, Direction.UP)
)

/*
* the adjacent side of side C of cube.
* */
private val cMap = mapOf(
	Direction.LEFT to Pair(Cube.A, Direction.UP),
	Direction.RIGHT to Pair(Cube.E, Direction.UP),
	Direction.UP to Pair(Cube.F, Direction.UP),
	Direction.DOWN to Pair(Cube.B,Direction.UP)
)

/*
* the adjacent side of side D of cube.
* */
private val dMap = mapOf(
	Direction.LEFT to Pair(Cube.A, Direction.DOWN),
	Direction.RIGHT to Pair(Cube.E,Direction.DOWN),
	Direction.UP to Pair(Cube.B,Direction.DOWN),
	Direction.DOWN to Pair(Cube.F, Direction.DOWN)
)

/*
* the adjacent side of side E of cube.
* */
private val eMap = mapOf(
	Direction.LEFT to Pair(Cube.B, Direction.RIGHT),
	Direction.RIGHT to Pair(Cube.F, Direction.LEFT),
	Direction.UP to Pair(Cube.C,Direction.RIGHT),
	Direction.DOWN to Pair(Cube.D, Direction.RIGHT)
)

/*
* the adjacent side of side F of cube.
* */
private val fMap = mapOf(
	Direction.LEFT to Pair(Cube.E,Direction.RIGHT),
	Direction.RIGHT to Pair(Cube.A,Direction.LEFT),
	Direction.UP to Pair(Cube.C,Direction.UP),
	Direction.DOWN to Pair(Cube.D, Direction.DOWN)
)

private val cubeMap = mapOf(
	Cube.A to aMap,
	Cube.B to bMap,
	Cube.C to cMap,
	Cube.D to dMap,
	Cube.E to eMap,
	Cube.F to fMap
)

fun trace(input:String):String{
	val ansStringBuilder = StringBuilder()

	val tileList = transformToTiles(input)
	val start = Pair(Cube.A, Direction.LEFT)
	var current = start
	ansStringBuilder.append(current.first.toChar)

	var next = getNextPoint(current,tileList)
	current = next

	while (current != start){
		ansStringBuilder.append(current.first.toChar)
		next = getNextPoint(current,tileList)
		current = next
	}

	return ansStringBuilder.toString()
}

/*
* Input current point, which side and what kind of direction,
* return next point, which side and direction, should be.
* */
private fun getNextPoint(current:Pair<Cube, Direction>, tileList: List<Map<Direction, Direction>>): Pair<Cube, Direction>{
	val position = cubeToPosition[current.first] ?: error("Can't find this side, ${current.first.toChar}, in cube.")
	val direction = tileList[position][current.second] ?: error("Unknown direction: ${current.second}")
	return cubeMap[current.first]?.get(direction)?: error("Can't find this side ${current.first} in cube, or can't find this direction, $direction in ${current.first} ")
}

/*
* Transform each char to correspond type of tile.
* */
private fun transformToTiles(input: String): List<Map<Direction, Direction>> {
	val result = mutableListOf<Map<Direction,Direction>>()

	for ((i,c) in input.withIndex()){
		if (charToTile.containsKey(c)){
			charToTile[c]?.let { result.add(i, it) }
		}
	}

	return result.toList()
}
