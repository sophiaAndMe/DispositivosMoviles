package com.example.myapplication.repositories.connections.remote

import com.example.myapplication.remote.dto.UserDtoRemote

interface UserRemote {

    suspend fun getAllUsers() : Result<List<UserDtoRemote>>
    suspend fun getOneUser(user: UserDtoRemote) : Result<UserDtoRemote?>
    suspend fun saveUser(user: UserDtoRemote) : Result<UserDtoRemote>


}