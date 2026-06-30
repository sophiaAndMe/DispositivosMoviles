package com.example.myapplication.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.repositories.connections.local.LocalDataBase

@Entity
data class ProfilesDto (

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    // recupero los datos de FireBase y despues puedo manejarlo localmente
    val idFireBase: String,
    val name : String,
    val imagen : String?
)