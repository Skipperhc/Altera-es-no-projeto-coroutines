package br.com.wellingtoncosta.coroutines.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.wellingtoncosta.coroutines.domain.model.User
import br.com.wellingtoncosta.coroutines.domain.model.UserAccess
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PagLoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModeScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val mUserAccess: MutableLiveData<UserAccess> = MutableLiveData()

    val userAccess: LiveData<UserAccess> get() = mUserAccess

    fun getUserAccess() {
        viewModeScope.launch {
            try {
                mUserAccess.value = repository.getUserAccess()
            } catch(t: Throwable) {
                mUserAccess.value = UserAccess(user = t.message!!, cpf = "iiiiiiiiih")
            } finally {

            }
        }
    }
}