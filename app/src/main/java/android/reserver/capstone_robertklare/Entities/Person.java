package android.reserver.capstone_robertklare.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "People")
public class Person {

    @PrimaryKey(autoGenerate = true)
    int personID;
    String firstName;
    String lastname;
    int teamID;

    public Person(String firstName, String lastname, int teamID) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.teamID = teamID;
    }

    public int getPersonID() {
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
