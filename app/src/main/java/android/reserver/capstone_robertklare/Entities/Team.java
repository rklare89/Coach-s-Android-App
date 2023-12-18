package android.reserver.capstone_robertklare.Entities;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(tableName = "Teams")
public class Team implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int teamId;
    public String teamName;
    public String ageGroup;
    public Date createdOn;


    @Ignore
    Set<Player> playerSet = new HashSet<>();


    public Team(String teamName, String ageGroup) {
        this.teamName = teamName;
        this.ageGroup = ageGroup;
        createdOn = new Date();

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

    public int getTeamId() {
        return teamId;
    }

    public Set<Player> getPlayerSet() {
        return playerSet;
    }

}
