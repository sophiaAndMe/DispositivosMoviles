package com.example.myapplication.repositories.connections.remote

import com.example.myapplication.remote.dto.UserDtoRemote

interface UserRemote {

    fun getAllUsers() : Result<List<UserDtoRemote>>
    fun getOneUser(user: UserDtoRemote) : Result<UserDtoRemote>
    fun saveUser(user: UserDtoRemote) : Result<UserDtoRemote>


}