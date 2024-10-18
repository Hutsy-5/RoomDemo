package com.samuelokello.roomdemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<User>>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Flow<User>

    @Insert
    suspend fun insertAll(vararg users: User)

    @Delete
    suspend fun delete(user: User)
}

//@Dao
//interface AdminDao {
//    @Query("SELECT * FROM admin")
//    fun getAll(): List<Admin>
//
////    @Query("SELECT * FROM admin WHERE adId IN (:adminIds)")
////    fun loadAllByIds(adminIds: IntArray): List<Admin>
////
////    @Query("SELECT * FROM admin WHERE first_name LIKE :first AND " +
////           "last_name LIKE :last LIMIT 1")
////    fun findByName(first: String, last: String): Admin
//
//    @Insert
//    fun insertAll(vararg admins: Admin)
//
//    @Delete
//    fun delete(admin: Admin)
//}