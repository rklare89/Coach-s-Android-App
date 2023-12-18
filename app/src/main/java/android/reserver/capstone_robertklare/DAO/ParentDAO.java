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
    long insert(Parent parent);

    @Update
    void update(Parent parent);

    @Delete
    void delete(Parent parent);

    @Query("SELECT * FROM Parents")
    LiveData<List<Parent>> getAllParents();

    @Query("SELECT * FROM Parents Where personID = :id")
    LiveData<Parent> getParentById(long id);

    @Query("SELECT * FROM Parents WHERE (firstName = :firstName AND lastname = :lastName);")
    LiveData<Parent> getParentByNames(String firstName, String lastName);

    @Query("SELECT firstName FROM Parents WHERE personID = :id;")
    String getParentFirstName(long id);

    @Query("SELECT lastname FROM Parents WHERE personID = :id;")
    String getParentLastName(long id);

    @Query("DELETE FROM Parents WHERE personID = :id;")
    void deleteParentByID(long id);
}
