package com.inviz.roleplayparty

import android.app.Application
import com.inviz.data.api.ApiService
import com.inviz.roleplayparty.di.DataModule
import com.inviz.roleplayparty.di.DomainModule
import com.inviz.roleplayparty.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RolePlayPartyApp : Application() {

    private val dataModule = DataModule(this)
    private val domainModule = DomainModule(this)
    private val presentationModule = PresentationModule(this)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RolePlayPartyApp)
            modules(
                listOf(
                    dataModule.dataModule,
                    domainModule.domainModule,
                    presentationModule.presentationModule
                )
            )
        }

        //TODO поменять сервер
        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
    }

}