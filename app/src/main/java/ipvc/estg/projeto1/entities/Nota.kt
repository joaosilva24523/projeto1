package ipvc.estg.projeto1.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nota_table")

class Nota(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "nota") val nota: String,
    @ColumnInfo(name = "descricao") val descicao: String
)
