package beeth0ven.cn.beatbox;

/**
 * Created by Air on 2017/2/6.
 */

public class Sound {
    public String assetPath;
    public String name;
    public Integer soundId;

    public Sound(String assetPath) {
        this.assetPath = assetPath;
        String [] components = assetPath.split("/");
        name = components[components.length - 1]
                .replace(".wav", "");
    }
}

