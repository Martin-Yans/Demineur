import controleur.Controleur
import controleur.ControleurRestartbutton
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Model
import vue.Vue


const val with : Double = 800.0
const val width : Double = 800.0

class des_mineurs : Application() {
    override fun start(primaryStage: Stage?) {
        val model = Model()
        val (rows, cols) = model.getRowColsTableGame()
        val vue = Vue(rows, cols)


        vue.setButton(model.getTableGame())
        
        vue.fixeControleurButtonRestart(ControleurRestartbutton(model, vue))
        vue.fixeControleurButtonSetText(Controleur(model, vue))


        val scene = Scene(vue, with, width)
        primaryStage?.scene = scene
        primaryStage?.show()
    }
}


fun main() {
    Application.launch(des_mineurs::class.java)
}