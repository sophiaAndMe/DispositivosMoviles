package com.example.myapplication.logic.usercases

import com.example.myapplication.data.remote.dto.users.TypicodeUserDtoItem
import com.example.myapplication.repositories.connections.TypiUsersRepository
import com.example.myapplication.repositories.connections.remote.TypiUserRemote
import retrofit2.create

class GetAllUsersFromTypi {

     suspend fun invoke() : List<TypicodeUserDtoItem>? {

         val baseApi = TypiUsersRepository().getApi()
         val call = baseApi.create<TypiUserRemote>().getAllUsersTypi()

         if (call.isSuccessful){
            val items =  call.body()
             return items
         }else{
             return emptyList()
         }
     }

}