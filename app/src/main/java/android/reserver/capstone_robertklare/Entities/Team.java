package android.reserver.capstone_robertklare.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.HashSet;
import java.util.Set;

@Entity(tableName = "Teams")
public class Team {

    @PrimaryKey(autoGenerate = true)
    public int teamId;
    public String teamName;
    public String ageGroup;
    @Ignore
    Set<Player> playerSet = new HashSet<>();

    public Team(String teamName, String ageGroup) {
        this.teamName = teamName;
        this.ageGroup = ageGroup;
    }
}
