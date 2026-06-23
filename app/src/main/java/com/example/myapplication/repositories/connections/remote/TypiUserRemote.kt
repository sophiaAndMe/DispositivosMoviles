package com.example.myapplication.repositories.connections.remote

import com.example.myapplication.data.remote.dto.users.TypicodeUserDtoItem
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.http.GET

// endpoint
interface TypiUserRemote {

    @GET("users")
    suspend fun getAllUsersTypi() : retrofit2.Response<List<TypicodeUserDtoItem>>


}