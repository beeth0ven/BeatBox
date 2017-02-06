package beeth0ven.cn.beatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Air on 2017/2/6.
 */

public class BeatBoxFragment extends Fragment {

    private BeatBox beatBox = new BeatBox();

    public static BeatBoxFragment newInstanse() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beat_box_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(beatBox.sounds));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beatBox.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Sound sound;
        private Button button;

        public SoundHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.sound_cell, viewGroup, false));
            button = (Button) itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }

        public void bindSound(Sound sound) {
            this.sound = sound;
            button.setText(sound.name);
        }

        @Override
        public void onClick(View v) {
            beatBox.play(sound);
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> sounds;

        public SoundAdapter(List<Sound> sounds) {
            this.sounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            holder.bindSound(sounds.get(position));
        }

        @Override
        public int getItemCount() {
            return sounds.size();
        }
    }
}
