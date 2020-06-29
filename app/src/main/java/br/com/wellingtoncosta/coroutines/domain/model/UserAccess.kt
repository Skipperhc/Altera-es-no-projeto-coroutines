package br.com.wellingtoncosta.coroutines.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserAccess")
class UserAccess(
    @ColumnInfo(name = "nome")
    val user: String,
    @ColumnInfo(name = "cpf")
    val cpf: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}