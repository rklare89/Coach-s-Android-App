package project.reserver.capstone_robertklare;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import project.reserver.capstone_robertklare.Database.Repository;
import project.reserver.capstone_robertklare.Entities.Player;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TeamDetails extends AppCompatActivity implements PlayerListAdapter.OnItemClickListener{

    private PlayerListAdapter adapter;
    Repository repo;
    private int teamID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        repo = new Repository(getApplication());

        // Sets up headers on page
        Intent intent = getIntent();
        String teamName = intent.getStringExtra("team");
        String teamAge = intent.getStringExtra("age");

        teamID = intent.getIntExtra("id", 0);



        TextView teamNameBox = findViewById(R.id.detailsTeamName);
        TextView teamAgeBox = findViewById(R.id.textView9);
        teamNameBox.setText(teamName);
        teamAgeBox.setText(teamAge);

        RecyclerView recyclerView = findViewById(R.id.playerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlayerListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        repo.getPlayersByTeamID(teamID).observe(this, player -> adapter.setPlayers(player));

        Button doneBtn = findViewById(R.id.doneBtnTD);
        doneBtn.setOnClickListener(v -> finish());

        Button deleteBtn = findViewById(R.id.deleteTeamButton);
        deleteBtn.setOnClickListener(v -> {

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {

                    repo.removePlayersFromTeam(teamID);
                    repo.deleteTeamById(teamID);
                    return null;
                }
            }.execute();

        finish();
        });

        Button addBtn = findViewById(R.id.addPlayerButton);
        addBtn.setOnClickListener(v -> {
            Intent addIntent = new Intent(TeamDetails.this, AddPlayer.class);
            addIntent.putExtra("id", teamID);
            addIntent.putExtra("age", teamAge);
            startActivity(addIntent);
        });

        Button exportBtn = findViewById(R.id.exportRoster);
        exportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repo.getPlayersByTeamID(teamID).observe(TeamDetails.this, player -> {
                    adapter.setPlayers(player);

                    generatePDF(teamName, (ArrayList<Player>) player);
                });
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        //Extracts Data for Detail Activity
        Player selectedPlayer = adapter.getPlayers().get(position);
        String playerFirst = selectedPlayer.getFirstName();
        String playerLast = selectedPlayer.getLastname();
        String playerPosition = selectedPlayer.getPosition();
        String playerDob = selectedPlayer.getDob();
        int playerNum = selectedPlayer.getNumber();
        int teamID = selectedPlayer.getTeamID();
        long parID = selectedPlayer.getParentID();
        Log.d("Parent ID:", "onItemClick: " + parID);
        long playerID = selectedPlayer.getPersonID();
        boolean isRostered = selectedPlayer.isRostered();

        //Packages intent for Details Activity
        Intent intent = new Intent(TeamDetails.this, PlayerDetails.class);
        intent.putExtra("firstName", playerFirst);
        intent.putExtra("lastName", playerLast);
        intent.putExtra("position", playerPosition);
        intent.putExtra("DOB", playerDob);
        intent.putExtra("number", playerNum);
        intent.putExtra("team", teamID);
        intent.putExtra("parent", parID);
        intent.putExtra("roster", isRostered);
        intent.putExtra("personID", playerID);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ResumeCalled", "onResume: Method Called");



        RecyclerView recyclerView = findViewById(R.id.playerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlayerListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        repo.getPlayersByTeamID(teamID).observe(this, player -> adapter.setPlayers(player));


    }


    private void generatePDF(String teamName, ArrayList<Player> players) {
        Document document = new Document();



        try {
            String fileName = "TeamDetails.pdf";
            File pdfFile = new File(getExternalFilesDir(null), fileName);
            String authority = getPackageName() + ".fileprovider";
            Uri contentUri = FileProvider.getUriForFile(this, authority, pdfFile);

            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));


            document.open();

            // Add team name at the top
            Font teamNameFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
            Paragraph teamNameParagraph = new Paragraph(teamName, teamNameFont);
            teamNameParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(teamNameParagraph);

            document.add(new Paragraph("\n")); // Add some space

            // Table for player details
            PdfPTable table = new PdfPTable(5); // 5 columns for player details
            table.setWidthPercentage(100);

            // Add table headers
            table.addCell("Player Name");
            table.addCell("Position");
            table.addCell("DOB");
            table.addCell("Number");
            table.addCell("Last Updated");



            // Add player details
            for (Player player : players) {
                table.addCell(player.getFirstName() + " " + player.getLastname());
                table.addCell(player.getPosition());
                table.addCell(player.getDob());
                table.addCell(String.valueOf(player.getNumber()));
                Date date = (player.getLastUpdated());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateS = dateFormat.format(date);
                table.addCell(dateS);
            }

            document.add(table);

            document.close();

            //Open the report
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(contentUri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}