package android.reserver.capstone_robertklare.Database;

import android.app.Application;
import android.os.AsyncTask;
import android.reserver.capstone_robertklare.DAO.ParentDAO;
import android.reserver.capstone_robertklare.DAO.PlayerDAO;
import android.reserver.capstone_robertklare.DAO.TeamDAO;
import android.reserver.capstone_robertklare.Entities.Parent;
import android.reserver.capstone_robertklare.Entities.Player;
import android.reserver.capstone_robertklare.Entities.Team;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;

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

    public Team getPlayerTeamByID(int teamId) { return mTeamDAO.getPlayerTeam(teamId);}

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

    public void deleteTeamById(int id) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mTeamDAO.deleteByTeamId(id);
        });
    }

    // Player operations
    public LiveData<List<Player>> getAllPlayers() {
        return mPlayerDAO.getAllPlayers();
    }

    public void removePlayersFromTeam(int teamID) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mPlayerDAO.removePlayersFromDeletedTeam(teamID);
                return null;
            }
        }.execute();
    }

    public LiveData<Player> getPlayerById(int playerId) {
        return mPlayerDAO.getPlayerById(playerId);
    }

    public LiveData<List<Player>> getPlayersByTeamID(int id) {
        return mPlayerDAO.getPlayersByTeamID(id);
    }

    public LiveData<List<Player>> getPlayersNotRostered() {
        return mPlayerDAO.getPlayersNotRostered();
    }

    public void insertPlayer(Player player) {
        databaseBuilder.databaseWriteExecutor.execute(() -> {
            mPlayerDAO.insert(player);
        });
    }

    public void deletePlayerByID(long id, long parentid){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mPlayerDAO.deletePlayerByID(id);
                mParentDAO.deleteParentByID(parentid);
                return null;
            }
        }.execute();
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

    public LiveData<Parent> getParentById(long parentId) {
        return mParentDAO.getParentById(parentId);
    }
    public LiveData<Parent> getParentByNames(String firstName, String LastName) {
        return mParentDAO.getParentByNames(firstName, LastName);
    }

    public String getParentFirstName(long id) {
        return mParentDAO.getParentFirstName(id);
    }
    public String getParentLastName(long id) {
        return mParentDAO.getParentLastName(id);
    }

    public long insertParent(Parent parent) {
        // Use a Callable to get the inserted ID
        Callable<Long> insertCallable = () -> mParentDAO.insert(parent);

        try {
            // Run the insert operation on a separate thread and get the ID
            return databaseBuilder.databaseWriteExecutor.submit(insertCallable).get();
        } catch (Exception e) {
            // Handle exceptions as needed
            return -1; // Or any other appropriate value indicating failure
        }
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
