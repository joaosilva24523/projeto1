package ipvc.estg.projeto1.db

import androidx.lifecycle.LiveData
import ipvc.estg.projeto1.dao.NotaDao
import ipvc.estg.projeto1.entities.Nota

class NotaReporitory(private val notaDao: NotaDao) {
    val allNotas: LiveData<List<Nota>> = notaDao.getAlphabetizedNotas()

    suspend fun insert(nota: Nota){
        notaDao.insert(nota)
    }
}