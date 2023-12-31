package project.reserver.capstone_robertklare.Database;

import android.content.Context;
import project.reserver.capstone_robertklare.DAO.ParentDAO;
import project.reserver.capstone_robertklare.DAO.PlayerDAO;
import project.reserver.capstone_robertklare.DAO.TeamDAO;
import project.reserver.capstone_robertklare.DataValidation.DateCoverter;
import project.reserver.capstone_robertklare.Entities.Parent;
import project.reserver.capstone_robertklare.Entities.Player;

import project.reserver.capstone_robertklare.Entities.Team;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Team.class, Player.class, Parent.class}, version = 2, exportSchema = false)
@TypeConverters(DateCoverter.class)
public abstract class databaseBuilder extends RoomDatabase {

    public abstract ParentDAO parentDAO();

    public abstract PlayerDAO playerDAO();

    public abstract TeamDAO teamDAO();

    private static volatile databaseBuilder INSTANCE;

    static databaseBuilder getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (databaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), databaseBuilder.class, "AppDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;

    }

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);
}


