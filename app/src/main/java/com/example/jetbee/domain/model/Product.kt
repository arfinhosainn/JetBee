package com.example.jetbee.domain.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Product(
    val name: String = "",
    val category: String= "",
    val image: String = "",
    val price: String = "",
    val tagline: String = "",
    @Contextual
    @ServerTimestamp var lastModified: Timestamp? = null,

    ) : Parcelable
