package com.linas.RobotGame.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.linas.RobotGame.*;
import com.linas.RobotGame.systems.*;
import com.linas.RobotGame.world.*;

public class GameScreen implements Screen
{
    private final GameWorld gameWorld;
    private final DrawingSystem drawingSystem;
    private PlayerState playerState;
    private FogSystem fogSystem;

    public GameScreen(final Main main)
    {
        FitViewport viewport = new FitViewport(7, 7);
        playerState = new PlayerState(0, 0);
        fogSystem = new FogSystem();
        gameWorld = new GameWorld(playerState);

        drawingSystem = new DrawingSystem(main.spriteBatch, viewport, gameWorld, playerState, fogSystem);
    }

    @Override
    public void render(float delta)
    {
        gameWorld.update(InputSystem.moveDirection());

        fogSystem.calculateFog(gameWorld, playerState);

        drawingSystem.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        drawingSystem.resize(width, height);
    }

    @Override
    public void show()
    {
    }

    @Override
    public void dispose()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }
}
