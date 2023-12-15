package android.reserver.capstone_robertklare.DAO;

import android.reserver.capstone_robertklare.Entities.Team;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Team team);

    @Update
    void update(Team team);

    @Delete
    void delete(Team team);

    @Query("SELECT * FROM teams")
    LiveData<List<Team>> getAllTeams();

    @Query("SELECT * FROM Teams WHERE teamId = :teamId")
    List<Team> getTeamById(int teamId);
}
