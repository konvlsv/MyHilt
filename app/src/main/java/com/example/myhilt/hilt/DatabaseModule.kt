package com.example.myhilt.hilt

import android.content.Context
import androidx.room.Room
import com.example.myhilt.data.local.AppDatabase
import com.example.myhilt.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context // Hilt сам умеет прокидывать контекст приложения
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_app_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        // Hilt берет созданную выше базу данных и вытягивает из неё DAO
        return database.userDao()
    }
}