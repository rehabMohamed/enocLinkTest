package com.coolshop.codechallenge.enoclink.login

import org.junit.Test

class ValidationsKtTest {

    @Test
    fun `isEmailValid() with valid email should return true`() {
        val email = "rehab.mohmad@gmail.com"
        val result = email.isEmailValid()
        assert(result)
    }

    @Test
    fun `isEmailValid() with invalid email should return false`() {
        val email = "rehab.mohmad@gmail"
        val result = email.isEmailValid().not()
        assert(result)
    }

    @Test
    fun `isPasswordValid() with valid password should return true`() {
        val password = "12jhg3"
        val result = password.isPasswordValid()
        assert(result)
    }

    @Test
    fun `isPasswordValid() with invalid password should return false`() {
        val password = "123"
        val result = password.isPasswordValid().not()
        assert(result)
    }
}