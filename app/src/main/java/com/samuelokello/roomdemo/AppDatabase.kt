package com.samuelokello.roomdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private var DATABASE_INSTANCE: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
             val tempInstance = DATABASE_INSTANCE

            // If the instance is not null, then return it
            if (tempInstance != null) {
                return tempInstance
            }

            // If the instance is null, then create the database
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries()
                    .build()

                DATABASE_INSTANCE = instance

                return instance
            }
        }
    }
}