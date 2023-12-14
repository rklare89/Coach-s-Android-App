package android.reserver.capstone_robertklare.DAO;

import android.reserver.capstone_robertklare.Entities.Team;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface TeamDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Team team);

    @Update
    void update(Team team);

    @Delete
    void delete(Team team);



}
