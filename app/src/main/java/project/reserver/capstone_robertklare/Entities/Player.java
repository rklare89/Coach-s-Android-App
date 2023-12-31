package project.reserver.capstone_robertklare.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "Players")
public class Player extends Person {

    String position;
    int number;
    String dob;
    long parentID;
    boolean isRostered;



    public Player(String firstName, String lastname, int teamID, String position, int number,
                  String dob, long parentID, boolean isRostered) {
        super(firstName, lastname, teamID);

        this.position = position;
        this.number = number;
        this.dob = dob;
        this.parentID = parentID;
        this.isRostered = isRostered;
    }

    @Ignore
    public Player(long personID, String firstName, String lastname, int teamID, String position, int number,
                  String dob, long parentID, boolean isRostered) {
        super(personID, firstName, lastname, teamID);

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public long getParentID() {
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
