package com.app.virtusa.data.model

import com.google.gson.annotations.SerializedName

data class Album(@field:SerializedName("userId") var userId: Int,
                 @field:SerializedName("id") var id: String,
                 @field:SerializedName("title") var title: String
)

