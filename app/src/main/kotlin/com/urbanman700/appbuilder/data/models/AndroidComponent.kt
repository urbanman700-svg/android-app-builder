package com.urbanman700.appbuilder.data.models

import java.io.Serializable

data class AndroidComponent(
    val id: String,
    val name: String,
    val type: ComponentType,
    val properties: Map<String, String> = emptyMap()
) : Serializable

enum class ComponentType {
    ACTIVITY, FRAGMENT, SERVICE, BROADCAST_RECEIVER, CONTENT_PROVIDER,
    LAYOUT, DRAWABLE, STRING, COLOR, DIMENSION, STYLE
}
