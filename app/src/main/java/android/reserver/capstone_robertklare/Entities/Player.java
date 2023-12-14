package android.reserver.capstone_robertklare.Entities;

import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "People")
public class Player extends Person {

    String position;
    int number;
    Date dob;
    int parentID;
    boolean isRostered;


    public Player(String firstName, String lastname, int teamID, String position, int number,
                  Date dob, int parentID, boolean isRostered) {
        super(firstName, lastname, teamID);

        this.position = position;
        this.number = number;
        this.dob = dob;
        this.parentID = parentID;
        this.isRostered = isRostered;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public boolean isRostered() {
        return isRostered;
    }

    public void setRostered(boolean rostered) {
        isRostered = rostered;
    }
}
