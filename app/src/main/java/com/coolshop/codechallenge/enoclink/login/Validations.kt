package com.coolshop.codechallenge.enoclink.login

import java.util.regex.Pattern

fun String.isEmailValid(): Boolean =
        EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid(): Boolean =
        length > 5


private val EMAIL_ADDRESS: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
)