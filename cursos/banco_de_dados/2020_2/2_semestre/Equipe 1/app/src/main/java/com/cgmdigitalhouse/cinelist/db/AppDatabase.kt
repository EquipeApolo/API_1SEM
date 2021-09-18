package com.cgmdigitalhouse.cinelist.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieCrossRefDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

@Database(
    entities = [ ListMovieEntity::class, ListMovieCrossRefEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun listMovieDao(): ListMovieDao
    abstract fun listMovieCrossRefDao(): ListMovieCrossRefDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        // Singleton
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "listmovie"
                ).build()
            }

            return INSTANCE!!
        }
    }
}
