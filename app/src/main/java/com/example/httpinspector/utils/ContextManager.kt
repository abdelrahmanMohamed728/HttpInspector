package com.example.httpinspector.utils

import android.content.Context

class ContextManager private constructor() {
    private var applicationContext: Context? = null

    // Initialize the Context if not already set
    fun initialize(context: Context) {
        if (applicationContext == null) {
            applicationContext = context.applicationContext
        }
    }

    // Get the stored Context
    fun getContext(): Context {
        if (applicationContext == null) {
            throw IllegalStateException("ContextManager must be initialized with a valid context")
        }
        return applicationContext!!
    }

    companion object {
        @Volatile
        private var instance: ContextManager? = null

        // Create or return the existing instance of ContextManager
        fun getInstance(): ContextManager {
            return instance ?: synchronized(this) {
                instance ?: ContextManager().also { instance = it }
            }
        }
    }
}
