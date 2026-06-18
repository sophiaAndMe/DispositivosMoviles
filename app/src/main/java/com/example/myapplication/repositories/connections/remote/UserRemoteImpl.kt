package com.example.myapplication.repositories.connections.remote

import com.example.myapplication.remote.dto.UserDtoRemote
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.tasks.await

class UserRemoteImpl(val db: FirebaseFirestore) : UserRemote{

    override suspend fun getAllUsers(): Result<List<UserDtoRemote>>  = runCatching{

            var lista = arrayListOf<UserDtoRemote>()
            db.collection("users")
            .get()
            .await().forEach {
                    lista.add(it.toObject(UserDtoRemote::class.java))
            }

        return@runCatching lista
    }

    override suspend fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote?> = runCatching{
        var lista = arrayListOf<UserDtoRemote>()
        db.collection("users")
            .whereEqualTo("name", user.name.toString())
            .get()
            .await().forEach{
                lista.add(it.toObject(UserDtoRemote::class.java))
            }

        return@runCatching lista.firstOrNull()
    }

    override suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote> {
        var resp = db.collection("users")
            .add(user)
            // devuelve un result o un Drawaitvall
            .await().runCatching{user}
        return resp
    }


}