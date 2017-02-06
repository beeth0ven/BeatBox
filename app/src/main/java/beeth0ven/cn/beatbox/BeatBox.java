package beeth0ven.cn.beatbox;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Air on 2017/2/6.
 */

public class BeatBox {

    private AssetManager assetManager;
    public List<Sound> sounds = new ArrayList<>();
    private SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

    public BeatBox() {
        assetManager = MyApplication.context.getAssets();
        loadSounds();
    }

    public void play(Sound sound) {
        if (sound.soundId == null) { return; }
        soundPool.play(sound.soundId, 1, 1, 1, 0, 1);
    }

    public void release() {
        soundPool.release();
    }

    private void loadSounds() {
        String [] soundNames;
        try {
            soundNames = assetManager.list("sample_sounds");
            Log.i("BeatBox", "Found " + soundNames.length + " sounds");
        } catch (IOException exception) {
            Log.e("BeatBox", "Could not list assets", exception);
            return;
        }

        for (String name: soundNames) {
            try {
                String assetPath = "sample_sounds/" + name;
                Sound sound = new Sound(assetPath);
                loadSound(sound);
                sounds.add(sound);
            } catch (IOException exception) {
                Log.e("BeatBox", "Could not sound " + name, exception);
            }
        }
        Log.i("BeatBox", "Found " + sounds.size() + " sounds Object");

    }

    private void loadSound(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = assetManager.openFd(sound.assetPath);
        sound.soundId = soundPool.load(assetFileDescriptor, 1);
    }
}
