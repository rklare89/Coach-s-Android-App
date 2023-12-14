package android.reserver.capstone_robertklare.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashSet;
import java.util.Set;

@Entity(tableName = "Teams")
public class Team {

    @PrimaryKey(autoGenerate = true)
    int teamId;
    String teamName;
    String ageGroup;
    Set<Player> playerSet = new HashSet<>();
}
