package com.coolshop.codechallenge.enoclink.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.coolshop.codechallenge.enoclink.BuildConfig
import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.data.UserManager
import com.coolshop.codechallenge.enoclink.data.network.EnocLinkApi
import com.coolshop.codechallenge.enoclink.data.network.UserRepositoryImp
import com.coolshop.codechallenge.enoclink.data.network.mocking.MockInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val appContext: Context) {

    @Provides
    @Singleton
    fun provideUserRepository(sharedPrefs: SharedPreferences): UserManager =
        UserManager(sharedPrefs, Gson())

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
            appContext.getSharedPreferences("ENOC_LINK_CHALLENGE", Context.MODE_PRIVATE);

    @Provides
    @Singleton
    fun provideNetworkDataSource(enocLinkApi: EnocLinkApi, userManager: UserManager): UserRepository =
            UserRepositoryImp(enocLinkApi, userManager)

    @Provides
    @Singleton
    fun provideEnocLinkApi(retrofit: Retrofit): EnocLinkApi {
        return retrofit.create(EnocLinkApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppRetrofit(client: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        return builder.client(client).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            authInterceptor: Interceptor?,
                            mockInterceptor: MockInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            // Log request and response headers in debugging mode
            httpClient.addInterceptor(loggingInterceptor)
            // mock backend response in debugging mode
            httpClient.addInterceptor(mockInterceptor)
        }
        authInterceptor?.let {
            httpClient.addInterceptor(it)
        }

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(userManager: UserManager): Interceptor? {
        return if (userManager.isLoggedIn) {
            Interceptor { chain: Interceptor.Chain ->
                val accessToken = userManager.loggedInUser?.apiToken
                val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $accessToken")
                        .build()
                chain.proceed(request)
            }
        } else {
            null
        }
    }

    @Provides
    @Singleton
    fun provideMockInterceptor(): MockInterceptor {
        return MockInterceptor()
    }

    companion object {
        private const val API_BASE_URL = "https://www.enoclink.ae/"
    }
}