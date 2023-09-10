package org.fuckrkn1.android.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.fuckrkn1.android.room.entity.CountryDB

@Dao
interface CountriesDAO {
    @Query( "SELECT * FROM countries")
    suspend fun getAllCountries(): List<CountryDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryDB)

    @Delete
    suspend fun delete(country: CountryDB)
}