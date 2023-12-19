package project.reserver.capstone_robertklare.Entities;

import project.reserver.capstone_robertklare.Enum.Role;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "Parents")
public class Parent extends Person {

    String phone;
    String email;
    Role role;

    public Parent(String firstName, String lastname, int teamID, String phone, String email, Role role) {
        super(firstName, lastname, teamID);
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    @Ignore
    public Parent(long personID, String firstName, String lastname, int teamID, String phone, String email, Role role) {
        super(personID, firstName, lastname, teamID);
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
