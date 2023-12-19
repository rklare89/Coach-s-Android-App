package project.reserver.capstone_robertklare.DAO;

import project.reserver.capstone_robertklare.Entities.Player;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Player player);

    @Delete
    void delete(Player player);

    @Update
    void update(Player player);

    @Query("SELECT * FROM Players")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM Players where personID = :id;")
    LiveData<Player> getPlayerById(int id);

    @Query("SELECT * FROM Players where (teamID = :teamID AND isRostered = 1)")
    LiveData<List<Player>> getPlayersByTeamID(int teamID);


    @Query("SELECT * FROM Players where isRostered = 0;")
    LiveData<List<Player>> getPlayersNotRostered();

    @Query("UPDATE Players SET isRostered = 0 WHERE teamID = :teamid")
    void removePlayersFromDeletedTeam(int teamid);

    @Query("DELETE FROM Players WHERE personID = :id;")
    void deletePlayerByID(long id);

    @Query("SELECT * FROM Players WHERE firstName LIKE :search OR lastname LIKE :search")
    LiveData<List<Player>> searchPlayers(String search);
}
