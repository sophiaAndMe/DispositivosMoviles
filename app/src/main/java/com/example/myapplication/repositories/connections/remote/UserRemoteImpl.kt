package com.example.myapplication.repositories.connections.remote

import com.example.myapplication.remote.dto.UserDtoRemote

class UserRemoteImpl : UserRemote{

    override fun getAllUsers(): Result<List<UserDtoRemote>> {
        TODO("Not yet implemented")
    }

    override fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: UserDtoRemote): Result<UserDtoRemote> {
        TODO("Not yet implemented")
    }


}