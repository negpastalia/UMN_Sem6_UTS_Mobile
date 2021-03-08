package id.ac.umn.uts_27962;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.LinkedList;

public class listLagu extends AppCompatActivity {
    RecyclerView daftarLagu;
    listLagu mAdapter;
    LinkedList<SongDetails> detailLagu = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lagu);

        isiDaftarLagu();
        daftarLagu = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new listLagu(this, detailLagu);
        daftarLagu.setAdapter(mAdapter);
        daftarLagu.setLayoutManager(new LinearLayoutManager(this));

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dropbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.do_goProfile:
                Intent gotoProfile = new Intent(listLagu.this, Profile.class);
                startActivityForResult(gotoProfile, 1);
                break;

            case R.id.do_goLogOut:
                Intent gotoMain = new Intent(listLagu.this, MainActivity.class);
                startActivityForResult(gotoMain, 1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void isiDaftarLagu(){
        detailLagu.add(new SongDetails("Song 1", R.raw.blueclapper));
    }
}