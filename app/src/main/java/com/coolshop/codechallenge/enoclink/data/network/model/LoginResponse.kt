package com.coolshop.codechallenge.enoclink.data.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("userid") val userId: String, val token: String)
