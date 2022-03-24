package interview.exam.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import interview.exam.data.local.dao.RoomDao;
import interview.exam.data.local.entity.GetItem;

@Database(entities = {GetItem.class}, version = 1)
public abstract class RoomDbHelper extends RoomDatabase {
    private static RoomDbHelper instance = null;
    public static final String DB_NAME = "RoomData.db";
    public static synchronized RoomDbHelper getInstance(Context context){
        if(instance == null){
            instance = create(context);//創立新的資料庫
        }
        return instance;
    }

    private static RoomDbHelper create(final Context context){
        return Room.databaseBuilder(context,RoomDbHelper.class,DB_NAME).build();
    }
    public abstract RoomDao getDataDao();
}
