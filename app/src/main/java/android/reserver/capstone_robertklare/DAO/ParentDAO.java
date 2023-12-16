package android.reserver.capstone_robertklare.DAO;

import android.reserver.capstone_robertklare.Entities.Parent;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ParentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Parent parent);

    @Update
    void update(Parent parent);

    @Delete
    void delete(Parent parent);

    @Query("SELECT * FROM Parents")
    LiveData<List<Parent>> getAllParents();

    @Query("SELECT * FROM Parents Where personID = :id")
    LiveData<Parent> getParentById(int id);

    @Query("SELECT personID FROM Parents WHERE (firstName = :firstName AND lastname = :lastName);")
    int getParentByNames(String firstName, String lastName);
}
