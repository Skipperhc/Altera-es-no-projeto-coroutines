package br.com.wellingtoncosta.coroutines.resources.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.wellingtoncosta.coroutines.domain.model.UserAccess

@Dao
interface UserAccessDAORoom {

    @Insert
    fun save(userAccess: UserAccess) : Int

    @Query("Select * FROM useraccess")
    fun getUsersAccess() : List<UserAccess>
}