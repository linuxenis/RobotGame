package com.linas.RobotGame.systems;

import com.linas.RobotGame.objects.*;
import com.linas.RobotGame.world.*;

import java.util.*;

public class CheckpointSystem
{
    ArrayList<GameObject> savedObjectList = new ArrayList<>();

    PlayerState savedPlayerState = new PlayerState();

    public void saveCheckpoint(GameWorld gameWorld, PlayerState playerState)
    {
        saveWorld(gameWorld);
        savePlayer(playerState);
    }

    public void saveWorld(GameWorld gameWorld)
    {
        savedObjectList.clear();
        for(GameObject gameObject : gameWorld.getAllObjects())
        {
            savedObjectList.add(gameObject.copy());
        }
    }

    public void savePlayer(PlayerState playerState)
    {
        savedPlayerState.loadState(playerState);
    }

    public void loadCheckpoint(GameWorld gameWorld, PlayerState playerState)
    {
        loadWorld(gameWorld);
        loadPlayer(playerState);
    }

    public void loadPlayer(PlayerState playerState)
    {
        playerState.loadState(savedPlayerState);
    }

    public void loadWorld(GameWorld gameWorld)
    {
        gameWorld.loadMap(savedObjectList);
    }
}
