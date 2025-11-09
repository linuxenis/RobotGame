package com.linas.RobotGame.systems;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.linas.RobotGame.enums.*;
import com.linas.RobotGame.objects.*;
import com.linas.RobotGame.world.*;

import java.util.*;

public class DrawingSystem
{
    private final SpriteBatch spriteBatch;
    private final FitViewport viewport;

    private final Texture backgroundTexture;
    private final Texture fogTexture;

    private GameWorld gameWorld;
    private PlayerState playerState;

    private float worldWidth;
    private float worldHeight;

    private FogSystem fogSystem;

    public DrawingSystem(SpriteBatch spriteBatch, FitViewport viewport, GameWorld gameWorld, PlayerState playerState, FogSystem fogSystem)
    {
        this.spriteBatch = spriteBatch;
        this.viewport = viewport;

        this.gameWorld = gameWorld;
        this.playerState = playerState;

        // Get textures from the asset manager
        this.backgroundTexture = AssetManager.getTexture("ground");
        this.fogTexture = AssetManager.getTexture("fogTile");
        this.worldWidth = viewport.getWorldWidth();
        this.worldHeight = viewport.getWorldHeight();

        this.fogSystem = fogSystem;
    }

    public void draw()
    {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();

        drawWorld();
        drawFog();
        drawUI();

        spriteBatch.end();
    }

    private void drawWorld()
    {
        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);

        for (GameObject gameObject : gameWorld.getAllObjects())
        {
            drawObject(gameObject);
        }

        spriteBatch.draw(playerState.getTexture(), 3, 3, 1, 1);
    }

    private void drawObject(GameObject gameObject)
    {
        int playerX = playerState.getCoordinates().x;
        int playerY = playerState.getCoordinates().y;
        int objectX = gameObject.getCoordinates().x;
        int objectY = gameObject.getCoordinates().y;

        int relativeObjectXToScreen = objectX - playerX + 3; //+3 because player is in the middle of the screen
        int relativeObjectYToScreen = objectY - playerY + 3;

        float objectWidth = gameObject.getWidth();
        float objectHeight = gameObject.getWidth();

        Texture objectTexture = gameObject.getTexture();

        spriteBatch.draw(objectTexture, relativeObjectXToScreen, relativeObjectYToScreen, objectWidth, objectHeight);
    }

    private void drawFog()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (fogSystem.getFogMap()[i][j])
                {
                    spriteBatch.draw(fogTexture, i, j, 1, 1);
                }
            }
        }
    }

    private void drawUI()
    {
        // Energy Bar
        float barWidth = 2f;
        float barHeight = 0.4f;
        float barX = 0.2f;
        float barY = 6.4f;
        spriteBatch.draw(AssetManager.getTexture("energyBarBackground"), barX, barY, barWidth, barHeight);
        float energyPercentage = (float) playerState.getEnergy() / playerState.getMaxEnergy();
        spriteBatch.draw(AssetManager.getTexture("energyBarForeground"), barX, barY, barWidth * energyPercentage, barHeight);

        // Modules
        List<ModuleType> modules = playerState.getModules();
        for (int i = 0; i < modules.size(); i++)
        {
            Texture moduleTexture = AssetManager.getTexture(modules.get(i).name().toLowerCase() + "Module");
            if (moduleTexture != null)
            {
                float modX = 6f - i;
                float modY = 6.1f;
                spriteBatch.draw(moduleTexture, modX, modY, 1, 1);
            }
        }
        float keyX = 6f;
        float keyY = 0.2f;
        Texture keyTexture = AssetManager.getTexture("key");
        if (playerState.hasKey())
        {
            spriteBatch.draw(keyTexture, keyX, keyY, 1, 1);
        }
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
    }
}
