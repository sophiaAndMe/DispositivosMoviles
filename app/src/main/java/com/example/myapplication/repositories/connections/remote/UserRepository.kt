package com.example.myapplication.repositories.connections.remote

import com.example.myapplication.remote.dto.UserDtoRemote



class UserRepository (val userRemoteImpl: UserRemote){

    suspend  fun saveUser(user : UserDtoRemote): Result<UserDtoRemote> {

       //var us =  userRemoteImpl.saveUser(user).getOrNull()

        return userRemoteImpl.saveUser(user)


    }

    suspend fun getAllUsers() : Result<List<UserDtoRemote>>{
        return userRemoteImpl.getAllUsers()
    }


}