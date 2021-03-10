package id.ac.umn.uts_27962;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import java.util.LinkedList;

public class listLagu extends AppCompatActivity {

    RecyclerView daftarLagu;
    SongListAdapter mAdapter;
    LinkedList<SongDetails> detailLagu = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_song);

        daftarLagu = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new SongListAdapter(this, detailLagu);
        daftarLagu.setAdapter(mAdapter);
        daftarLagu.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.menu_ToolbarListLagu);
        setSupportActionBar(toolbar);

        isiDaftarLagu();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.namePop) + "\n" + getString(R.string.nimPop))
                .setTitle(getString(R.string.titlePop))
                .setPositiveButton(getString(R.string.btnPop), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { dialog.dismiss(); }
                });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_dropbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.do_goProfile:
                Intent gotoProfile = new Intent(listLagu.this, Profile.class);
                startActivityForResult(gotoProfile, 2);
                break;

            case R.id.do_goLogOut:
                Intent gotoMain = new Intent(listLagu.this, MainActivity.class);
                startActivityForResult(gotoMain, 3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void isiDaftarLagu(){
        detailLagu.add(new SongDetails("Answer", "android.resource://" +getPackageName() + "/" + R.raw.answer));
        detailLagu.add(new SongDetails("Blue Clapper", "android.resource://" +getPackageName() + "/" + R.raw.blueclapper));
        detailLagu.add(new SongDetails("Fiction", "android.resource://" +getPackageName() + "/" + R.raw.fiction));
        detailLagu.add(new SongDetails("NAVIGATOR", "android.resource://" +getPackageName() + "/" + R.raw.navigator));
        detailLagu.add(new SongDetails("PANTA RHEI", "android.resource://" +getPackageName() + "/" + R.raw.pantarhei));
        detailLagu.add(new SongDetails("Suspect", "android.resource://" +getPackageName() + "/" + R.raw.suspect));
    }
}