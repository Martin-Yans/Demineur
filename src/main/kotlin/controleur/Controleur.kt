package controleur
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import modele.Model
import vue.Vue

class Controleur(model : Model, vue : Vue): EventHandler<MouseEvent> {

    private val model : Model
    private val vue : Vue

    private var mineIsDraw : Boolean?

    init{
        this.model = model
        this.vue = vue
        this.mineIsDraw = null
    }

    override fun handle(event : MouseEvent){
        val flag =  "🚩"

        if (mineIsDraw != null) return



        val button = event.source as Button
        val (i, j) = button.userData as Pair<Int, Int>

        val tableGame = model.getTableGame()

        println("v  ${button.text}")

        when (event.button){
            MouseButton.PRIMARY -> {
                val allSlot = model.findAllSlot(i,j)

                for (slot in allSlot) {
                    val (slotI, slotJ) = slot
                    val tabButton = vue.getTableButton()

                    if (tableGame[slotI][slotJ]!! == -1) {
                        drawAllMine()
                        vue.endMessage(endGameMessage())
                        break
                    }
                    val (row, cols) = model.getRowColsTableGame()

                    if (model.getCountVisitedCase() >= ((row * cols) - model.getMaxMine())) {
                        mineIsDraw = false
                        vue.endMessage(endGameMessage())
                    }


                    if (tabButton[slotI][slotJ]!!.text == flag) continue

                    vue.setText(slotI,slotJ, tableGame[slotI][slotJ].toString())
                }
            }
            MouseButton.SECONDARY -> {

                if (button.text == flag) {
                    vue.setText(i,j,  "")
                }else {
                    vue.setText(i,j,  flag)
                }
            }
            else -> {}
        }
    }

    fun drawAllMine(){
        val mine = "💣"
        val tableAllMine = model.getAllMine()

        for (tableElement in tableAllMine) {

            if (tableElement == null) continue

            val (i, j) = tableElement
            vue.setText(i,j, mine)
        }
        mineIsDraw = true

    }


    fun endGameMessage() : String{

        if (mineIsDraw == null) return ""

        val winMessage = "you win"
        val loseMessage = "you loose"

        return if (mineIsDraw!!) loseMessage else winMessage
    }




}