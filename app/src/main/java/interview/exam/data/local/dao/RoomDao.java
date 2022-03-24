package interview.exam.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import javax.inject.Inject;

import interview.exam.data.local.entity.GetItem;
import io.reactivex.Observable;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(GetItem item);
    @Query("SELECT * FROM room_entity WHERE id LIKE :id")
    public Observable<GetItem> findById(String id);
    @Query("SELECT * FROM room_entity WHERE name LIKE :name")
    public Observable<GetItem> findByName(String name);

    @Query("SELECT * FROM room_entity")
    Observable<List<GetItem>> getAll();
    @Delete
    public void delete(GetItem item);
    @Update
    public void update(GetItem item);
}
