package com.example.httpinspector.utils

import io.github.jan.supabase.gotrue.user.UserInfo

class BaseUser {
    companion object {
        var user: UserInfo? = null
        fun setUser(user: UserInfo?) {
            this.user = user
        }
    }
}