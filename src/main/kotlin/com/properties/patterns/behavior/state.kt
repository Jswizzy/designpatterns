package com.properties.patterns.behavior

sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val userLogin: String) : AuthorizationState()

class AuthorizationPresenter {

    private var state: AuthorizationState =
        Unauthorized

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userLogin: String
        get() {
            val state = this.state //val allows smart casting of state
            return when (state) {
                is Authorized -> state.userLogin
                is Unauthorized -> "Unknown"
            }
        }

    override fun toString() = "User '$userLogin' is logged in: $isAuthorized"
}

fun main() {
    AuthorizationPresenter().run {
        loginUser("admin")
        println(this)
        logoutUser()
        println(this)
    }
}