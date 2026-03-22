package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import modele.Model
import vue.Vue



class ControleurRestartbutton(model : Model, vue : Vue): EventHandler<ActionEvent> {
    private val model : Model
    private val vue : Vue

    init{
        this.model = model
        this.vue = vue
    }

    override fun handle(event: ActionEvent) {
        val button = event.source as Button
        println("hello")
        model.setGame()
        vue.setButton(model.getTableGame())


    }

}