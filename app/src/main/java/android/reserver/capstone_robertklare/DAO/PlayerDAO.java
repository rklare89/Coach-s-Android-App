package android.reserver.capstone_robertklare.DAO;

import android.reserver.capstone_robertklare.Entities.Player;

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



}
