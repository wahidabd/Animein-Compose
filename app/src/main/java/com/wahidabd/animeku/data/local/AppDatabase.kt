package com.wahidabd.animeku.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.animeku.data.local.anime.dto.AnimeDao
import com.wahidabd.animeku.data.local.anime.dto.AnimeEntity


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


@Database(entities = [AnimeEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "anime.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }

    abstract fun animeDao(): AnimeDao

}