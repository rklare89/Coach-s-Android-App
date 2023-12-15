package android.reserver.capstone_robertklare.Database;

import android.app.Application;
import android.reserver.capstone_robertklare.DAO.ParentDAO;
import android.reserver.capstone_robertklare.DAO.PlayerDAO;
import android.reserver.capstone_robertklare.DAO.TeamDAO;
import android.reserver.capstone_robertklare.Entities.Parent;
import android.reserver.capstone_robertklare.Entities.Player;
import android.reserver.capstone_robertklare.Entities.Team;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private ParentDAO mParentDAO;
    private PlayerDAO mPlayerDAO;
    private TeamDAO mTeamDAO;

    private LiveData<List<Team>> mTeams;
    private LiveData<List<Player>> mPlayer;
    private LiveData<List<Parent>> mParent;


    public Repository(Application application) {
        databaseBuilder database = databaseBuilder.getDatabase(application);
        mTeamDAO = database.teamDAO();
        mPlayerDAO = database.playerDAO();
        mParentDAO = database.parentDAO();

    }

    // Team operations
    public LiveData<List<Team>> getAllTeams() {
        return mTeamDAO.getAllTeams();
    }

    public List<Team> getTeamById(int teamId) {
        return mTeamDAO.getTeamById(teamId);
    }

    public void insertTeam(Team team) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mTeamDAO.insert(team);
        });
    }

    public void updateTeam(Team team) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mTeamDAO.update(team);
        });
    }

    public void deleteTeam(Team team) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mTeamDAO.delete(team);
        });
    }

    // Player operations
    public LiveData<List<Player>> getAllPlayers() {
        return mPlayerDAO.getAllPlayers();
    }

    public LiveData<Player> getPlayerById(int playerId) {
        return mPlayerDAO.getPlayerById(playerId);
    }

    public LiveData<List<Player>> getPlayersByTeamID(int id) {
        return mPlayerDAO.getPlayersByTeamID(id);
    }

    public void insertPlayer(Player player) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mPlayerDAO.insert(player);
        });
    }

    public void updatePlayer(Player player) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mPlayerDAO.update(player);
        });
    }

    public void deletePlayer(Player player) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mPlayerDAO.delete(player);
        });
    }



    // Parent operations
    public LiveData<List<Parent>> getAllParents() {
        return mParentDAO.getAllParents();
    }

    public LiveData<Parent> getParentById(int parentId) {
        return mParentDAO.getParentById(parentId);
    }

    public void insertParent(Parent parent) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mParentDAO.insert(parent);
        });
    }

    public void updateParent(Parent parent) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mParentDAO.update(parent);
        });
    }

    public void deleteParent(Parent parent) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mParentDAO.delete(parent);
        });


}}
