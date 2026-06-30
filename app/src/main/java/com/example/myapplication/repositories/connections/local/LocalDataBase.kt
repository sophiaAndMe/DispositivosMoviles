package com.example.myapplication.repositories.connections.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.daos.local.ProfilesDAO
import com.example.myapplication.data.local.dto.ProfilesDto

                                                    // a una nueva version, no afecta a la base datos pasada
@Database(entities = [ProfilesDto::class], version = 1)
abstract class LocalDataBase : RoomDatabase(){

    abstract fun profilesDao() : ProfilesDAO




}