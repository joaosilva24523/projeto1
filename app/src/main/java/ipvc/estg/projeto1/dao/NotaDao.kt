package ipvc.estg.projeto1.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.estg.projeto1.entities.Nota

@Dao
interface NotaDao {
    @Query("SELECT * FROM nota_table ORDER BY id ASC")
    fun getAlphabetizedNotas(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Nota)

    @Query("DELETE FROM nota_table")
    suspend fun deleteAll()
}