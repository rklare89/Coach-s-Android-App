package android.reserver.capstone_robertklare.DAO;

import android.reserver.capstone_robertklare.Entities.Player;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface PlayerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Player player);

    @Delete
    void delete(Player player);

    @Update
    void update(Player player);



}
