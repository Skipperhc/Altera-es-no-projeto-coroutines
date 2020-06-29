package br.com.wellingtoncosta.coroutines.resources.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.wellingtoncosta.coroutines.domain.model.UserAccess

@Database(entities = [UserAccess::class], version = 1)
abstract class UserAccessDatabaseRoom() : RoomDatabase() {

    abstract fun userAccessDAORoom(): UserAccessDAORoom

    companion object {
        private lateinit var INSTANCE: UserAccessDatabaseRoom
        fun getDatabase(context: Context): UserAccessDatabaseRoom {
            if(!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, UserAccessDatabaseRoom::class.java, "userAccess")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}