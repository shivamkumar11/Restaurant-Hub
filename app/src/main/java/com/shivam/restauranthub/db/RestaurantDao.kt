package com.shivam.restauranthub.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.shivam.restauranthub.database.RestaurantEntity

@Dao
interface RestaurantDao {
        @Insert
        fun insertRestaurant(restaurantEntity: RestaurantEntity)


        @Delete
        fun deleteRestaurant(restaurantEntity: RestaurantEntity)

        @Query("SELECT * FROM restaurant")
        fun getAllRestaurants(): List<RestaurantEntity>

        @Query("SELECT * FROM restaurant WHERE resId=:resid")
        fun getRestaurantById(resid: String): RestaurantEntity


    }

