package com.example.myapplication.repositories.connections

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TypiUsersRepository {


    fun getApi (): Retrofit {
        val baseConection = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return baseConection
    }

}