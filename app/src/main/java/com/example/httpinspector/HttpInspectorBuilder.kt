package com.example.httpinspector

import android.content.Context
import com.example.httpinspector.utils.ContextManager

public class HttpInspectorBuilder {
    companion object {
        fun initialize(context: Context) {
            ContextManager.getInstance().initialize(context)
        }
    }
}