package interview.exam.dependency_injection.module;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interview.exam.data.local.dao.RoomDao;
import interview.exam.data.local.database.RoomDbHelper;

@Module
public class RoomModule {
    @Singleton
    @Provides
    RoomDbHelper providesRoomDatabase(Application application){
        return Room.databaseBuilder(application, RoomDbHelper.class,RoomDbHelper.DB_NAME).build();
    }
    @Singleton
    @Provides
    RoomDao providesRoomDao(RoomDbHelper roomDbHelper){
        return roomDbHelper.getDataDao();
    }
}
