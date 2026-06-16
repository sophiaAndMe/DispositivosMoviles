package com.example.myapplication.logic.usercases

import com.example.myapplication.remote.dto.UserDtoRemote
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SaveUserUC {


    // inyecccion de dependencias
     suspend fun saveUser(
        user: UserDtoRemote,
        db : FirebaseFirestore) : Result<UserDtoRemote>
    {
        var resp = db.collection("users")
            .add(user)
            // devuelve un result o un Drawaitvall
            .await().runCatching{user}
        return resp
    }

}