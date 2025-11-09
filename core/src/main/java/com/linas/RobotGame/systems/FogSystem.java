package com.linas.RobotGame.systems;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.linas.RobotGame.world.*;

public class FogSystem
{
    private boolean[][] fogMap = new boolean[7][7];

    public void calculateFog(GameWorld gameWorld, PlayerState playerState)
    {
        fillFogMap();
        clearFogInTile(new GridPoint2(3, 3), playerState.getCoordinates(), gameWorld, playerState.getVisibilityLevel());
    }

    public void draw(SpriteBatch spriteBatch)
    {
        Texture fogTexture = AssetManager.getTexture("fogTile");
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (fogMap[i][j])
                {
                    spriteBatch.draw(fogTexture, i, j, 1, 1);
                }
            }
        }
    }

    private void fillFogMap()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                fogMap[i][j] = true;
            }
        }
    }

    private void clearFogInTile(GridPoint2 screenCoordinates, GridPoint2 worldCoordinates, GameWorld gameWorld, int visibility)
    {
        if (visibility == 0 || screenCoordinates.x > 6 || screenCoordinates.y > 6 || screenCoordinates.x < 0 || screenCoordinates.y < 0)
        {
            return;
        }
        fogMap[screenCoordinates.x][screenCoordinates.y] = false;

        if (!gameWorld.tileIsSolid(worldCoordinates))
        {
            clearFogInTile(screenCoordinates.cpy().add(0, 1), worldCoordinates.cpy().add(0, 1), gameWorld, visibility - 1);
            clearFogInTile(screenCoordinates.cpy().add(0, -1), worldCoordinates.cpy().add(0, -1), gameWorld, visibility - 1);

            clearFogInTile(screenCoordinates.cpy().add(1, 0), worldCoordinates.cpy().add(1, 0), gameWorld, visibility - 1);
            clearFogInTile(screenCoordinates.cpy().add(-1, 0), worldCoordinates.cpy().add(-1, 0), gameWorld, visibility - 1);
        }
    }

    public boolean[][] getFogMap()
    {
        return fogMap;
    }
}
