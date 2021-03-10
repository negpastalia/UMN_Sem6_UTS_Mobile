package id.ac.umn.uts_27962;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ItemSongViewHolder> {
    private LinkedList<SongDetails> mDaftarSong;
    private LayoutInflater mInflater;
    private Context mContext;

    public SongListAdapter(Context context, LinkedList<SongDetails> daftarSong) {
        this.mContext = context;
        this.mDaftarSong = daftarSong;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemSongViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_list_lagu,
                parent, false);
        return new ItemSongViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSongViewHolder holder, int position) {
        SongDetails mSumberLagu = mDaftarSong.get(position);
        holder.songTitle.setText(mSumberLagu.getTitle());
        holder.songUri.setText(mSumberLagu.getSongURI());
    }

    @Override
    public int getItemCount() {
        return mDaftarSong.size();
    }

    class ItemSongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView songTitle;
        private TextView songUri;
        private SongListAdapter mAdapter;
        private int mPosisi;
        private SongDetails mSumberLagu;

        public ItemSongViewHolder(@NonNull View itemView, SongListAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            songTitle = (TextView) itemView.findViewById(R.id.songTitle);
            songUri = (TextView) itemView.findViewById(R.id.songUri);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            mSumberLagu = mDaftarSong.get(mPosisi);

            Intent detilInten = new Intent(mContext, NowPlaying.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("PosisiLagu",mPosisi);
            bundle.putSerializable("DaftarLagu",mDaftarSong);
            detilInten.putExtras(bundle);
            mContext.startActivity(detilInten);
        }
    }
}


