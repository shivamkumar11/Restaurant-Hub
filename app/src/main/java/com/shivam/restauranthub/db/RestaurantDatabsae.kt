package com.shivam.restauranthub.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shivam.restauranthub.database.RestaurantEntity


@Database(entities = [RestaurantEntity::class],version=1)
    abstract class RestaurantDatabase: RoomDatabase() {
        abstract fun restaurantDao():RestaurantDao

    }

