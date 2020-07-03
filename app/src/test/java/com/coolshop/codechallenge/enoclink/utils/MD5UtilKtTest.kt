package com.coolshop.codechallenge.enoclink.utils

import org.junit.Test

import org.junit.Assert.*

class MD5UtilKtTest {

    @Test
    fun `md5() with email returns valid md5 hash`() {
        val email = "myemailaddress@example.com"
        val result = email.md5()
        val expected = "0bc83cb571cd1c50ba6f3e8a78ef1346"
        assertEquals(result, expected)
    }
}