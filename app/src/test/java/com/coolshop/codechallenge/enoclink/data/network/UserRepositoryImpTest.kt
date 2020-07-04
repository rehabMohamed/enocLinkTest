package com.coolshop.codechallenge.enoclink.data.network

import com.coolshop.codechallenge.enoclink.data.UserManager
import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.data.network.model.LoginResponse
import com.coolshop.codechallenge.enoclink.data.network.model.UserResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImpTest {

    lateinit var userRepository: UserRepository
    @Mock
    lateinit var enocLinkApi: EnocLinkApi
    @Mock
    lateinit var userManager: UserManager

    @Before
    fun setup() {

        userRepository = spy(UserRepositoryImp(enocLinkApi, userManager))
    }

    @Test
    fun `login() with success response then save token and id`() {

        runBlocking {
            `when`(enocLinkApi.login(any())).thenReturn(LoginResponse("", ""))
            userRepository.login("", "")
            verify(userManager).updateUser(any(), any(), any(), any())
        }
    }

    @Test
    fun `login() with failed response then don't save token and id`() {

        runBlocking {
            `when`(enocLinkApi.login(any())).thenReturn(null)
            userRepository.login("", "")
            verify(userManager, never()).updateUser(any(), any(), any(), any())
        }
    }

    @Test
    fun `fetchUserProfile() when no profile photo get Gravatar photo`() {
        runBlocking {
            `when`(enocLinkApi.getUser(any())).thenReturn(UserResponse("", null))
            userRepository.fetchUserProfile()
            verify(userRepository).getGravatarUrl(any())
        }
    }

    @Test
    fun `fetchUserProfile() when there is profile photo never get Gravatar photo`() {
        runBlocking {
            `when`(enocLinkApi.getUser(any())).thenReturn(UserResponse("", ""))
            userRepository.fetchUserProfile()
            verify(userRepository, never()).getGravatarUrl(any())
        }
    }

    fun <T> any(): T = Mockito.any<T>()
}