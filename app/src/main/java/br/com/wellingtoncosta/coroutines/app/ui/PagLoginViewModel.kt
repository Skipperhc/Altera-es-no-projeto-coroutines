package br.com.wellingtoncosta.coroutines.app.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.wellingtoncosta.coroutines.domain.model.User
import br.com.wellingtoncosta.coroutines.domain.model.UserAccess
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import br.com.wellingtoncosta.coroutines.resources.room.UserAccessRepositoryRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext

class PagLoginViewModel(
    private val repository: UserRepository,
    context: Context
) : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModeScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val mRepositoryRoom = UserAccessRepositoryRoom(context)

    private val mUserAccess: MutableLiveData<UserAccess> = MutableLiveData()

    val userAccess: LiveData<UserAccess> get() = mUserAccess

    private val mUserAccessExists: MutableLiveData<Boolean> = MutableLiveData()

    val userAccessExists: LiveData<Boolean> get() = mUserAccessExists

    fun getUserAccess(context: Context) {
        viewModeScope.launch {
            try {
                val userAccess = mRepositoryRoom.getUserAccess()
                if(userAccess != null) {
                    mUserAccess.value = userAccess
                } else {
                    mUserAccess.value = repository.getUserAccess()
                }
            } catch(t: Throwable) {
                t.message?.let {
                    mUserAccess.value = UserAccess(user = it, cpf = "iiiiiiiiih")
                }
            } finally {

            }
        }
    }
}