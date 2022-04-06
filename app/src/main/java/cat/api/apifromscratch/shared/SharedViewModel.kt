package cat.api.apifromscratch.shared

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private var idSeleccionada = -1
    private var nom = ""

    fun setId(id: Int) {
        idSeleccionada = id
    }

    fun getId(): Int {
        return idSeleccionada
    }

    fun setNom(name: String) {
        nom = name
    }

    fun getNom(): String {
        return nom
    }

}