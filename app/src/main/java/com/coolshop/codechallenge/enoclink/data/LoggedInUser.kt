package com.coolshop.codechallenge.enoclink.data

data class LoggedInUser(var userId: String,
                        var email: String,
                        var password: String,
                        var avatarUrl: String? = null,
                        var apiToken: String
)