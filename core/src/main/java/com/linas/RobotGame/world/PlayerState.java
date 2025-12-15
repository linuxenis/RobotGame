package com.linas.RobotGame.world;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.linas.RobotGame.enums.*;
import com.linas.RobotGame.objects.Module;
import com.linas.RobotGame.systems.*;

import java.util.*;

public class PlayerState
{
    public static final int MAX_MODULES = 3;

    private boolean key = false;
    private int energy = 50;
    private int maxEnergy = 50;
    private int moveCost = 1;

    private int visibilityLevel = 2;

    private GridPoint2 coordinates;
    private Direction facingDirection = Direction.DOWN;

    private HashMap<Direction, Texture> textures = new HashMap<>();

    private ArrayList<ModuleType> modules = new ArrayList<>();
    private int modulesCount = 0;

    public PlayerState(int x, int y)
    {
        this.coordinates = new GridPoint2(x, y);
        textures.put(Direction.DOWN, AssetManager.getTexture("robotDown"));
        textures.put(Direction.UP, AssetManager.getTexture("robotUp"));
        textures.put(Direction.RIGHT, AssetManager.getTexture("robotRight"));
        textures.put(Direction.LEFT, AssetManager.getTexture("robotLeft"));
    }

    public PlayerState()
    {
        textures.put(Direction.DOWN, AssetManager.getTexture("robotDown"));
        textures.put(Direction.UP, AssetManager.getTexture("robotUp"));
        textures.put(Direction.RIGHT, AssetManager.getTexture("robotRight"));
        textures.put(Direction.LEFT, AssetManager.getTexture("robotLeft"));
    }

    public Texture getTexture()
    {
        return textures.get(facingDirection);
    }

    public void setFacingDirection(Direction direction)
    {
        this.facingDirection = direction;
    }

    public void addCoordinates(GridPoint2 addedCoordinates)
    {
        coordinates.add(addedCoordinates);
    }

    public GridPoint2 getCoordinates()
    {
        return coordinates;
    }

    public boolean hasKey()
    {
        return key;
    }

    public void setKey(boolean key)
    {
        this.key = key;
    }

    public int getEnergy()
    {
        return energy;
    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public int getMaxEnergy()
    {
        return maxEnergy;
    }

    public void increaseMaxEnergy()
    {
        maxEnergy += Module.ENERGY_INCREMENT;
        energy += Module.ENERGY_INCREMENT;
    }

    public int getVisibilityLevel()
    {
        return visibilityLevel;
    }

    public void increaseVisibilityLevel()
    {
        this.visibilityLevel++;
    }

    public void decreaseVisibilityLevel()
    {
        this.visibilityLevel--;
    }

    public ArrayList<ModuleType> getModules()
    {
        return modules;
    }

    public int getModulesCount()
    {
        return modulesCount;
    }

    public void addModule(ModuleType moduleType)
    {
        modules.add(moduleType);
        modulesCount++;
    }

    public void takeDamage()
    {
        ModuleType lastModule = modules.get(modulesCount - 1);
        if (lastModule == ModuleType.FULLHEALTH)
        {
            modules.set(modulesCount - 1, ModuleType.DAMAGEDHEALTH);
            return;
        }
        else if (lastModule == ModuleType.ENERGY)
        {
            maxEnergy -= 25;
            if (energy > maxEnergy)
            {
                energy = maxEnergy;
            }
        }
        else if (lastModule == ModuleType.LIGHT)
        {
            decreaseVisibilityLevel();
        }
        modules.remove(modulesCount - 1);
        modulesCount--;
    }

    public void loadState(PlayerState playerState)
    {
        this.energy = playerState.energy;
        this.maxEnergy = playerState.maxEnergy;
        this.visibilityLevel = playerState.visibilityLevel;
        this.coordinates = playerState.coordinates.cpy();
        this.key = playerState.key;
        this.facingDirection = playerState.facingDirection;

        this.modules.clear();
        this.modules.addAll(playerState.getModules());
        this.modulesCount = modules.size();
    }

    public void spendMovementEnergy()
    {
        energy -= moveCost;
    }

}
