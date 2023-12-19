package project.reserver.capstone_robertklare.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "People")
public class Person {

    @PrimaryKey(autoGenerate = true)
    long personID;
    String firstName;
    String lastname;
    public Date lastUpdated;

    int teamID;

    public Person(String firstName, String lastname, int teamID) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.teamID = teamID;
        lastUpdated = new Date();

    }

    @Ignore
    public Person(long personID, String firstName, String lastname, int teamID) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastname = lastname;
        this.teamID = teamID;
        lastUpdated = new Date();
    }

    public long getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
