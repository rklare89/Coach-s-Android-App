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

    public Team() {};

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Set<Player> getPlayerSet() {
        return playerSet;
    }

}
