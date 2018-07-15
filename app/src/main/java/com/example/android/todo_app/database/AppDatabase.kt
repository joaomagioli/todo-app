package com.example.android.todo_app.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

/**
 * Class that will represent the app database
 */
@Database(entities = [TaskModel::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
 abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    // Making sure that different activities will use the same instance
    object DatabaseFactory {

        private const val DATABASE_NAME = "todolist"

        // Will hold the single instance
        lateinit var database: AppDatabase

        // Method that will generate DAO instance for us
        fun getInstance(context: Context) : AppDatabase {
            if (::database.isInitialized) return database
            database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
            return database
        }

    }

}