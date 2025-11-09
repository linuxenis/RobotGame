package com.linas.RobotGame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.linas.RobotGame.screens.*;
import com.linas.RobotGame.systems.*;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game implements ApplicationListener
{
    public SpriteBatch spriteBatch;

    @Override
    public void create()
    {
        spriteBatch = new SpriteBatch();
        AssetManager.load(); // Load all assets at the start

        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void dispose()
    {
        spriteBatch.dispose();
        AssetManager.dispose();
    }
}
