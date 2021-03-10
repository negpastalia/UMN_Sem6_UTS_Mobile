package id.ac.umn.uts_27962;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class NowPlaying extends AppCompatActivity {
    private ImageButton buttonPlayPause, buttonPrev, buttonNext;
    private TextView fillTitleSong;
    private MediaPlayer mediaPlayer;
    private SeekBar songProgress;
    private int posisi;
    private ArrayList<SongDetails> arraySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        Toolbar toolbar = (Toolbar)findViewById(R.id.menu_ToolbarPlay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonPlayPause = (ImageButton) findViewById(R.id.buttonPlayPause);
        buttonPrev = (ImageButton) findViewById(R.id.buttonPrev);
        buttonNext = (ImageButton) findViewById(R.id.buttonNext);
        songProgress = (SeekBar) findViewById(R.id.songProgress);
        fillTitleSong = (TextView) findViewById(R.id.fillTitleSong);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        posisi = (int) bundle.getSerializable("PosisiLagu");
        arraySong = (ArrayList<SongDetails>) bundle.getSerializable("DaftarLagu");

        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    buttonPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    songProgress.removeCallbacks(runnable);
                } else {
                    mediaPlayer.start();
                    buttonPlayPause.setImageResource(R.drawable.ic_baseline_pause_24);
                    songSeekBar();
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posisi ++;
                if(posisi > arraySong.size() - 1){
                    posisi --;
                } else {
                    songController();
                }
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posisi --;
                if(posisi < 0){
                    posisi ++;
                } else {
                    songController();
                }
            }
        });

        songController();
        songProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){ mediaPlayer.seekTo(progress); }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mediaPlayer.stop();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void songController(){
        try{
            mediaPlayer.release();
        } catch (Exception e){
            Log.i("Test", "Debug Only");
        }
        SongDetails Lagu = arraySong.get(posisi);
        fillTitleSong.setText(Lagu.getTitle());
        mediaPlayer = MediaPlayer.create(this, Uri.parse(Lagu.getSongURI()));
        mediaPlayer.start();

        songProgress.setMax(mediaPlayer.getDuration());
        songSeekBar();
    }

    private void songSeekBar(){
        songProgress.setProgress(mediaPlayer.getCurrentPosition());
        songProgress.postDelayed(runnable, 100);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            songSeekBar();
        }
    };
}