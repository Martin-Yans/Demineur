package vue

import javafx.event.ActionEvent
import javafx.scene.layout.GridPane
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.stage.Screen
import kotlin.collections.indices

class Vue(rows: Int, cols: Int) : GridPane() {
    private val tabButton : Array<Array<Button?>>

    private val buttonRestart = Button("Restart")
    init {
        require(rows > 0) { "rows must be > 0" }
        require(cols > 0) { "cols must be > 0" }
        this.tabButton = Array(rows) { arrayOfNulls<Button>(cols) }
    }

    fun setButton( table : Array<Array<Int?>>){
        children.clear()
        for (i in table.indices){
            for (j in table[i].indices){
                if (table[i][j] == null) {
                    this.tabButton[i][j] = null
                    continue
                }

                val button = Button()
                button.minWidth = 50.0
                button.minHeight = 50.0
                button.style = "-fx-background-radius: 0;"
                button.userData = Pair(i,j)

                this.tabButton[i][j] = button
                this.add(button, j, i)
            }
        }
    }

    fun setText(line :Int, culomn : Int, text : String){
        this.tabButton[line][culomn]!!.text = text
    }


    fun fixeControleurButtonSetText(control : EventHandler<MouseEvent>){
        for (i in tabButton.indices) {
            for (j in tabButton[i].indices) {
                if (tabButton[i][j] == null) continue

                tabButton[i][j]!!.onMouseClicked = control
            }
        }
    }

    fun fixeControleurButtonRestart(control : EventHandler<ActionEvent>){
        buttonRestart.onAction = control
    }




    fun getTableButton() : Array<Array<Button?>> {
        return tabButton
    }



    fun endMessage(message : String){

        val label = Label(message)


        label.style = """
            |-fx-text-fill: white;
            |-fx-font-size : 30px;
            |-fx-padding : 20 ;
        """.trimMargin()

        val stack = StackPane()

        stack.style = """|-fx-background-color : rgba(128,128,128,0.5) ;""".trimMargin()
        stack.children.addAll(label, buttonRestart)
        StackPane.setAlignment(label, Pos.CENTER)
        StackPane.setAlignment(buttonRestart, Pos.CENTER)
        label.translateY = -50.0
        buttonRestart.translateY = 50.0

        val rows = tabButton.size
        val cols = tabButton.firstOrNull()?.size ?: 0

        GridPane.setColumnSpan(stack, cols)
        GridPane.setRowSpan(stack, rows)
        GridPane.setHalignment(stack, HPos.CENTER)
        GridPane.setValignment(stack, VPos.CENTER)

        this.add(stack,0, 0)

    }
}