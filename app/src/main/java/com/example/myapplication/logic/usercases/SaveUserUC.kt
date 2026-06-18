package com.example.myapplication.logic.usercases

import com.example.myapplication.remote.dto.UserDtoRemote
import com.example.myapplication.repositories.connections.remote.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SaveUserUC (val userRepository: UserRepository){


    // inyecccion de dependencias
     suspend fun saveUser(user: UserDtoRemote) : Result<UserDtoRemote>
    {
        return userRepository.saveUser(user)

    }



}