package com.linas.RobotGame.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class AssetManager
{
    private static final HashMap<String, Texture> textures = new HashMap<>();
    private static final HashMap<String, Sound> sounds = new HashMap<>();
    private static Music music;

    public static void load()
    {
        textures.put("ground", new Texture("ground.png"));

        textures.put("robotUp", new Texture("robotUp.png"));
        textures.put("robotDown", new Texture("robotDown.png"));
        textures.put("robotLeft", new Texture("robotLeft.png"));
        textures.put("robotRight", new Texture("robotRight.png"));

        textures.put("wall", new Texture("wall.png"));
        textures.put("doorClosed", new Texture("doorClosed.png"));
        textures.put("doorOpen", new Texture("doorOpen.png"));
        textures.put("exit", new Texture("exit.png"));

        textures.put("key", new Texture("key.png"));
        textures.put("trap", new Texture("trap.png"));
        textures.put("checkpoint", new Texture("checkpoint.png"));

        textures.put("lightModule", new Texture("lightModule.png"));
        textures.put("energyModule", new Texture("energyModule.png"));
        textures.put("fullhealthModule", new Texture("healthModuleFull.png"));
        textures.put("damagedhealthModule", new Texture("healthModuleDamaged.png"));

        textures.put("fogTile", new Texture("fogTile.png"));

        textures.put("energyBarBackground", new Texture("energyBarBackground.png"));
        textures.put("energyBarForeground", new Texture("energyBarForeground.png"));

        sounds.put("robotMoveSound", Gdx.audio.newSound(Gdx.files.internal("robotMove.mp3")));
        sounds.put("explosionSound", Gdx.audio.newSound(Gdx.files.internal("explosion.mp3")));
        sounds.put("pickupSound", Gdx.audio.newSound(Gdx.files.internal("itemPickup.mp3")));
        sounds.put("unlockSound", Gdx.audio.newSound(Gdx.files.internal("doorUnlock.mp3")));

        music = Gdx.audio.newMusic(Gdx.files.internal("caveAmbience.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    public static Texture getTexture(String name)
    {
        return textures.get(name);
    }

    public static Sound getSound(String name)
    {
        return sounds.get(name);
    }

    public static void dispose()
    {
        for (Texture texture : textures.values())
        {
            texture.dispose();
        }
        for (Sound sound : sounds.values())
        {
            sound.dispose();
        }
        music.dispose();
    }
}
