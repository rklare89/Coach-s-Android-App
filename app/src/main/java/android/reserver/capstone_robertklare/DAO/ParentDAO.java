package android.reserver.capstone_robertklare.DAO;

import android.reserver.capstone_robertklare.Entities.Parent;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface ParentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Parent parent);

    @Update
    void update(Parent parent);

    @Delete
    void delete(Parent parent);
}