package com.shivam.restauranthub.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="restaurant")
data class RestaurantEntity(
    @PrimaryKey val resId: Int,
    @ColumnInfo(name = "res_Name") val resName: String?,
    @ColumnInfo(name = "res_Price") val resPrice: String?,
    @ColumnInfo(name = "res_Image") val resImage: String?,
    @ColumnInfo(name = "res_Rating") val resRating: String?

    /* val resRating: String,
     val resPrice: String,
     val resImage: String*/
)