package com.linas.RobotGame.world;

import com.badlogic.gdx.math.*;
import com.linas.RobotGame.enums.*;
import com.linas.RobotGame.objects.*;
import com.linas.RobotGame.systems.*;

import java.util.*;

public class GameWorld
{
    private HashMap<GridPoint2, List<GameObject>> objectsMap = new HashMap<>();
    private ArrayList<GameObject> allOBjects = new ArrayList<>();

    PlayerState playerState;
    CheckpointSystem checkpointSystem = new CheckpointSystem();

    public GameWorld(PlayerState playerState)
    {
        AllObjectsCreator.create(this);
        this.playerState = playerState;
        checkpointSystem.saveCheckpoint(this, playerState);
    }

    public void update(Direction moveDirection)
    {
        if (moveDirection == null) return;

        GridPoint2 moveCoordinates = directionToGridPoint2(moveDirection);
        if (!canMove(moveCoordinates, playerState.getCoordinates())) return;

        playerState.setFacingDirection(moveDirection);
        movePlayer(moveCoordinates);

        interactWithObjects();
    }

    public void interactWithObjects()
    {
        GridPoint2 playerCoordinates = playerState.getCoordinates();
        if (objectsMap.get(playerCoordinates) != null)
        {
            for (GameObject object : new ArrayList<>(objectsMap.get(playerCoordinates))) //if I iterate through the same list and not a new one, it bugs out
            {
                object.interact(this, playerState);
            }
        }
    }

    private void movePlayer(GridPoint2 moveCoordinates)
    {
        AssetManager.getSound("robotMoveSound").play();
        playerState.addCoordinates(moveCoordinates);
        playerState.spendMovementEnergy();
        if (playerState.getEnergy() <= 0)
        {
            AssetManager.getSound("explosionSound").play();
            checkpointSystem.loadCheckpoint(this, playerState);
        }
    }

    public boolean canMove(GridPoint2 moveCoordinates, GridPoint2 currentCoordinates)
    {
        GridPoint2 newCoordinates = currentCoordinates.cpy().add(moveCoordinates); //.cpy makes a temporary copy
        List<GameObject> objects = objectsMap.getOrDefault(newCoordinates, Collections.emptyList()); //getOrDefault returns objects of new coordinates or an empty list instead of null

        for (GameObject object : objects)
        {
            if (!object.isPassable(playerState)) {
                return false;
            }
        }

        return true;
    }

    public boolean tileIsSolid(GridPoint2 coordinates)
    {
        if (objectsMap.get(coordinates) != null)
        {
            for (GameObject object : objectsMap.get(coordinates))
            {
                if (object.isSolid())
                {
                    return true;
                }
            }
        }
        return false;
    }


    private GridPoint2 directionToGridPoint2(Direction direction)
    {
        int x = 0, y = 0;
        switch (direction)
        {
            case UP:
                y = 1;
                break;
            case DOWN:
                y = -1;
                break;
            case RIGHT:
                x = 1;
                break;
            case LEFT:
                x = -1;
                break;
        }
        return new GridPoint2(x, y);
    }

    public void addGameObject(GameObject gameObject)
    {
        if (gameObject == null) return;

        allOBjects.add(gameObject);

        GridPoint2 objectCoordinates = gameObject.getCoordinates();
        if (objectsMap.get(objectCoordinates) == null)
        {
            objectsMap.put(objectCoordinates, new ArrayList<>());
        }
        objectsMap.get(objectCoordinates).add(gameObject);
    }

    public void removeGameObject(GameObject gameObject)
    {
        if (gameObject == null) return;

        allOBjects.remove(gameObject);

        GridPoint2 objectCoordinates = gameObject.getCoordinates();
        if (objectsMap.containsKey(objectCoordinates))
        {
            objectsMap.get(objectCoordinates).remove(gameObject);
            if (objectsMap.get(objectCoordinates).isEmpty())
            {
                objectsMap.remove(objectCoordinates);
            }
        }
    }

    public void damagePlayer()
    {
        if (playerState.getModulesCount() == 0)
        {
            checkpointSystem.loadCheckpoint(this, playerState);
        }
        else
        {
            playerState.takeDamage();
        }
    }

    public ArrayList<GameObject> getAllObjects()
    {
        return allOBjects;
    }

    public HashMap<GridPoint2, List<GameObject>> getObjectsMap()
    {
        return objectsMap;
    }

    public void loadMap(ArrayList<GameObject> savedObjectList)
    {
        allOBjects.clear();
        for (GameObject savedObject : savedObjectList)
        {
            allOBjects.add(savedObject.copy());
        }
        generateMapFromList();
    }

    public void generateMapFromList()
    {
        objectsMap.clear();
        for (GameObject gameObject : allOBjects)
        {
            GridPoint2 coordinates = gameObject.getCoordinates();
            if (objectsMap.get(coordinates) == null)
            {
                objectsMap.put(coordinates, new ArrayList<>());
            }
            objectsMap.get(coordinates).add(gameObject);
        }
    }

    public void saveGame()
    {
        checkpointSystem.saveCheckpoint(this, playerState);
    }
}
