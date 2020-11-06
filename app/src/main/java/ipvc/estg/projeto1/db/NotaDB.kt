package ipvc.estg.projeto1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.estg.projeto1.dao.NotaDao
import ipvc.estg.projeto1.entities.Nota
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Nota::class), version = 6,exportSchema = false)
public abstract class NotaDB : RoomDatabase() {
    abstract fun notaDao(): NotaDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch{
                    var notaDao = database.notaDao()

                    //Delete all content
                    notaDao.deleteAll()

                    //Add 2 lines
                    var nota = Nota(1,"teste","teste descricao")
                    notaDao.insert(nota)
                    nota = Nota(2,"teste2","teste 2 descricao")
                    notaDao.insert(nota)
                }
            }
        }

    }

    companion object{
        @Volatile
        private var INSTANCE: NotaDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotaDB{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotaDB::class.java,
                    "notas_database"
                )
                    //estratégia de destruição
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}