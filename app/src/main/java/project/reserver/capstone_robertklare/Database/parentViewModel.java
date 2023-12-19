package project.reserver.capstone_robertklare.Database;

import android.app.Application;

import project.reserver.capstone_robertklare.Entities.Parent;
import project.reserver.capstone_robertklare.Entities.Player;
import project.reserver.capstone_robertklare.Entities.Team;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class parentViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Team>> allTeams;
    private LiveData<List<Player>> allPlayers;
    private LiveData<List<Parent>> allParents;

    public parentViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTeams = repository.getAllTeams();
        allPlayers = repository.getAllPlayers();
        allParents = repository.getAllParents();
    }




    // Team operations
    public LiveData<List<Team>> getAllTeams() {
        return allTeams;
    }

    public List<Team> getTeamById(int teamId) {
        return repository.getTeamById(teamId);
    }

    // Player operations
    public LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }

    public LiveData<Player> getPlayerById(int playerId) {
        return repository.getPlayerById(playerId);
    }

    public LiveData<List<Player>> getPlayersByTeamID(int id) {
        return repository.getPlayersByTeamID(id);
    }

    // Parent operations
    public LiveData<List<Parent>> getAllParents() {
        return allParents;
    }

    public LiveData<Parent> getParentById(long parentId) {
        return repository.getParentById(parentId);
    }

    public LiveData<Parent> getParentByNames(String firstName, String LastName) {
        return repository.getParentByNames(firstName, LastName);
    }

    public String getParentFirstName(long id) {
        return repository.getParentFirstName(id);
    }

    public String getParentLastName(long id) {
        return repository.getParentLastName(id);
    }

    public long insertParent(Parent parent) {
        return repository.insertParent(parent);
    }

    public void updateParent(Parent parent) {
        repository.updateParent(parent);
    }

    public void deleteParent(Parent parent) {
        repository.deleteParent(parent);
    }
}
