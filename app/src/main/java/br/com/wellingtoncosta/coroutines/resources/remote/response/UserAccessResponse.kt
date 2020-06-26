package br.com.wellingtoncosta.coroutines.resources.remote.response

import br.com.wellingtoncosta.coroutines.domain.model.User
import br.com.wellingtoncosta.coroutines.domain.model.UserAccess
import com.squareup.moshi.Json

data class UserAccessResponse (
    @Json(name = "user") val user: String,
    @Json(name = "cpf") val cpf: String,
    @Json(name = "sucess") val sucess: Boolean
)

fun UserAccessResponse.toModel() = UserAccess(this.user, this.cpf)