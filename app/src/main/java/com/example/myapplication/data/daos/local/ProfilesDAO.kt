package com.example.myapplication.data.daos.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.myapplication.data.local.dto.ProfilesDto


@Dao
interface ProfilesDAO {


    @Query("SELECT * FROM ProfilesDto")
    fun getAllProfiles() : List<ProfilesDto>

                                                        // parametro
    @Query("SELECT * from ProfilesDto where id =:idProfile")
    fun geyOneProfile(idProfile : Int) : ProfilesDto


    @Insert(onConflict = REPLACE)
    fun insertProfile(profile : ProfilesDto)


}