package org.critical.antibuild;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class AntiBuild extends JavaPlugin {

    private FileConfiguration lockedWorldsConfig;
    private File lockedWorldsFile;
    private Set<String> lockedWorlds;
    private long loadStartTime;
    private int loadErrors;

    @Override
    public void onEnable() {
        loadStartTime = System.currentTimeMillis();
        loadErrors = 0;

        createLockedWorldsConfig();
        lockedWorlds = new HashSet<>(lockedWorldsConfig.getStringList("Locked-Worlds"));

        getCommand("antibuild").setExecutor(new AntiBuildCommandExecutor(this));
        getServer().getPluginManager().registerEvents(new AntiBuildEventHandler(this), this);

        long loadEndTime = System.currentTimeMillis();
        long loadDuration = loadEndTime - loadStartTime;

        getLogger().info("AntiBuild loaded in " + loadDuration + " ms with " + loadErrors + " errors.");

        if (loadDuration > 100) {
            getLogger().warning("AntiBuild took over 100 ms to load.");
        }
    }

    @Override
    public void onDisable() {
        if (lockedWorlds != null) {
            saveLockedWorldsConfig();
        }
    }

    public void createLockedWorldsConfig() {
        lockedWorldsFile = new File(getDataFolder(), "lockedworlds.yml");
        if (!lockedWorldsFile.exists()) {
            lockedWorldsFile.getParentFile().mkdirs();
            try {
                lockedWorldsFile.createNewFile();
                lockedWorldsConfig = YamlConfiguration.loadConfiguration(lockedWorldsFile);
                lockedWorldsConfig.set("Locked-Worlds", new ArrayList<String>());
                lockedWorldsConfig.save(lockedWorldsFile);
            } catch (IOException e) {
                getLogger().log(Level.SEVERE, "Could not create lockedworlds.yml", e);
                loadErrors++;
            }
        } else {
            lockedWorldsConfig = YamlConfiguration.loadConfiguration(lockedWorldsFile);
        }
    }

    public void saveLockedWorldsConfig() {
        try {
            lockedWorldsConfig.set("Locked-Worlds", new ArrayList<>(lockedWorlds));
            lockedWorldsConfig.save(lockedWorldsFile);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not save lockedworlds.yml", e);
            loadErrors++;
        }
    }

    public void addLockedWorld(String worldName) {
        lockedWorlds.add(worldName);
        saveLockedWorldsConfig();
    }

    public void removeLockedWorld(String worldName) {
        lockedWorlds.remove(worldName);
        saveLockedWorldsConfig();
    }

    public boolean isLockedWorld(String worldName) {
        return lockedWorlds.contains(worldName);
    }

    public String getLockedWorldMessage() {
        return "You cannot build in this world.";
    }

    public String getDropItemsMessage() {
        return "You cannot drop items in this world.";
    }
}
