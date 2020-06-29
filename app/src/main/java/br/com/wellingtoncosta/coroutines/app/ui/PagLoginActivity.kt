package br.com.wellingtoncosta.coroutines.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.wellingtoncosta.coroutines.R
import br.com.wellingtoncosta.coroutines.databinding.ActivityPagLoginBinding as Biding
import br.com.wellingtoncosta.coroutines.resources.room.UserAccessRepositoryRoom
import kotlinx.android.synthetic.main.activity_pag_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PagLoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<PagLoginViewModel>()

    private lateinit var mRepositoryRoom: UserAccessRepositoryRoom

    private val binding by lazy {
        DataBindingUtil.setContentView<Biding>(this, R.layout.activity_pag_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        mRepositoryRoom = UserAccessRepositoryRoom(this)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.userAccess.observe(this, Observer {
            TextUser.text = it.user
            TextCpf.text = it.cpf
        })
    }

    private fun setupListeners() {
        ButtonAvancar.setOnClickListener {
            if(viewModel.userAccess.value != null) {
                with(mRepositoryRoom) {
                    save(viewModel.userAccess.value!!)
                }
            }
            startActivity(Intent(this, ListUsersActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserAccess(this)
    }
}
