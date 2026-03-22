package modele
import kotlin.collections.get
import kotlin.random.Random
import kotlin.random.nextInt


class Model {

    private var tableGame: Array<Array<Int?>>
    private var tableOfMine: Array<Pair<Int, Int>?>
    private val maxMine: Int
    private val rowTableGame : Int
    private val colsTableGame : Int
    private var countVisitedCase: Int = 0


    init {
        rowTableGame = 10
        colsTableGame = 10
        tableGame = Array(rowTableGame) { arrayOfNulls(colsTableGame) }

        maxMine = 5
        tableOfMine = arrayOfNulls(maxMine)
        setRandomMine()
        setNumberOfCase()
    }

    fun setGame(){
        tableGame = Array(rowTableGame) { arrayOfNulls(colsTableGame) }
        tableOfMine = arrayOfNulls(maxMine)
        setRandomMine()
        setNumberOfCase()
    }


    fun setRandomMine() {
        var count: Int = 0
        while (count < maxMine) {
            val i = Random.nextInt(tableGame.size)
            val j = Random.nextInt(tableGame[i].size)
            if (tableGame[i][j] == -1) continue
            tableGame[i][j] = -1
            addInTableOfMine(i,j)
            count++
        }
    }


    fun setNumberOfCase() {
        for (i in tableGame.indices) {
            for (j in tableGame[i].indices) {
                if (tableGame[i][j] == -1) {
                    continue
                }
                val count = getCountNumber(i, j)
                tableGame[i][j] = count
            }
        }
    }

    fun addInTableOfMine(i: Int, j : Int){
        for( tabIndex in 0 .. tableOfMine.size-1){
            if (tableOfMine[tabIndex] == null){
                tableOfMine[tabIndex] = Pair(i, j)
                break
            }
        }
    }




    fun getCountNumber(iInit: Int, jInit: Int): Int {
        var count = 0
        for (i in 0..2) {
            for (j in 0..2) {
                val line = iInit - 1 + i
                val column = jInit - 1 + j

                if (line !in tableGame.indices) continue
                if (column !in tableGame[line].indices) continue

                if (tableGame[line][column] == -1) {
                    count++
                }
            }
        }
        return count
    }

    fun findAllSlot(line : Int, column :Int,
                    visited : Array<BooleanArray> = Array(tableGame.size){ BooleanArray(tableGame[0].size)})
                    : List<Pair<Int, Int>>{
        
        if (line < 0 ||line >= tableGame.size || column < 0 ||column >= tableGame[line].size)  return emptyList()
        if (tableGame[line][column] != 0 ) {
            if (!visited[line][column]) {
                visited[line][column] = true
                countVisitedCase++
            }
            return mutableListOf(Pair(line, column))
        }
        if (visited[line][column]) return emptyList()

        visited[line][column] = true
        countVisitedCase ++

        val result = mutableListOf(Pair(line, column))

        for (i in -1..1) {
            for (j in -1..1) {
                if (i != 0 || j != 0) {
                    result += findAllSlot(line + i, column + j, visited)
                }
            }
        }

        return result
    }



    fun getTableGame(): Array<Array<Int?>> {
        return tableGame
    }

    fun getAllMine(): Array<Pair<Int, Int>?> {
        return tableOfMine
    }

    fun getMaxMine() : Int {
        return maxMine
    }

    fun getRowColsTableGame(): Pair<Int, Int>{
        return Pair(rowTableGame, colsTableGame)
    }

    fun getCountVisitedCase() : Int{
        return countVisitedCase
    }
}


