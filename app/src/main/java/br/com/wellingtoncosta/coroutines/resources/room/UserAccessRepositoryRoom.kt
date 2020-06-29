package br.com.wellingtoncosta.coroutines.resources.room

import android.content.Context
import br.com.wellingtoncosta.coroutines.domain.model.UserAccess

class UserAccessRepositoryRoom(context: Context) {

    private val mDatabase = UserAccessDatabaseRoom.getDatabase(
        context = context
    ).userAccessDAORoom()

    fun save(userAccess: UserAccess) {
        mDatabase.save(userAccess = userAccess)
    }

    fun getUserAccess() : UserAccess {
        return mDatabase.getUsersAccess()[0]
    }

}