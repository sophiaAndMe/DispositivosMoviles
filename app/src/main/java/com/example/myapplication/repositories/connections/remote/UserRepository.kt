package com.example.myapplication.repositories.connections.remote

import com.example.myapplication.data.local.dto.ProfilesDto
import com.example.myapplication.remote.dto.UserDtoRemote
import com.example.myapplication.repositories.connections.local.LocalDataBase


class UserRepository (val userRemoteImpl: UserRemote,
                      val dbLocal : LocalDataBase){

    suspend  fun saveUser(user : UserDtoRemote): Result<UserDtoRemote> {

        val resp : Result<UserDtoRemote>

        val usRemote = userRemoteImpl.saveUser(user).getOrNull()


        if(usRemote != null) {
            var profileDto = ProfilesDto(
                0,
                usRemote.id,
                usRemote.name,
                usRemote.imagen)

            dbLocal.profilesDao().insertProfile(profileDto)
            resp = Result.success(usRemote)
        } else {
            resp = Result.failure(Exception("No se guardo el usuario de forma remota"))
        }

        return resp
    }

    suspend fun getAllUsers() : Result<List<UserDtoRemote>>{
        return userRemoteImpl.getAllUsers()
    }


}