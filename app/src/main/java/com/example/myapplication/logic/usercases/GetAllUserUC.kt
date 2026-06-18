package com.example.myapplication.logic.usercases

import com.example.myapplication.remote.dto.UserDtoRemote
import com.example.myapplication.repositories.connections.remote.UserRepository

class GetAllUserUC  (val userRespository : UserRepository){

    suspend fun invoke() : Result<List<UserDtoRemote>>{
        return userRespository.getAllUsers()
    }

}