/* This file is part of InternalPluginManager
 * 
 * Copyright 2014 Blockhaus2000
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.plugin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.CommandRegistrationUtil;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * An implmenetation of {@link IpmPlugin}.
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmPlugin implements IpmPlugin {
    protected final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private IpmMain main;

    private IpmPluginDescription description;
    private String name;

    private FileConfiguration config;
    private File configFile;

    private File dataFolder;

    private boolean enabled = false;

    /**
     * Instances a new {@link SimpleIpmPlugin}.
     * 
     * <p>
     * <b> NOTE: Only the {@link IpmPluginManager} creates a new
     * {@link SimpleIpmPlugin}. </b>
     * </p>
     */
    public SimpleIpmPlugin() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
            return;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#init(IpmPluginDescription)
     */
    @Override
    public void init(final IpmPluginDescription description) {
        changes.addPropertyChangeListener(this);

        this.description = description;

        dataFolder = new File(main.getDataFolder() + File.separator + "plugins" + File.separator + description.getName()
                + File.separator);
        configFile = new File(dataFolder.getPath() + File.separator + "config.yml");
        config = new YamlConfiguration();
        reloadConfig();

        name = this.description.getName();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#onLoad()
     */
    @Override
    public void onLoad() {
        // empty
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#onDisable()
     */
    @Override
    public void onDisable() {
        // empty
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#onEnable()
     */
    @Override
    public void onEnable() {
        // empty
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#reload()
     */
    @Override
    public void reload() {
        SimpleIpmPluginManager.getInstance().reload(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#saveConfig()
     */
    @Override
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#reloadConfig()
     */
    @Override
    public void reloadConfig() {
        try {
            config.load(configFile);
        } catch (FileNotFoundException ex) {
            // fails silent
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
        } catch (InvalidConfigurationException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#loadConfig()
     */
    @Override
    public void loadConfig() {
        reloadConfig();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public void registerCommands(final Class<?> clazz, final Object obj) {
        CommandRegistrationUtil.registerCommands(clazz, obj, main);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class)
     */
    @Override
    public void registerCommands(final Class<?> clazz) {
        registerCommands(clazz, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Object)
     */
    @Override
    public void registerCommands(final Object obj) {
        registerCommands(obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#setEnabled(boolean)
     */
    @Override
    public void setEnabled(final boolean enabled) {
        boolean oldValue = this.enabled;

        this.enabled = enabled;

        changes.firePropertyChange("enabled", oldValue, enabled);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.logging.Level,
     *      java.lang.Object)
     */
    @Override
    public <T> void log(final Level level, final T msg) {
        ChatOut.log(level, msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.lang.Object)
     */
    @Override
    public <T> void log(final T msg) {
        ChatOut.log(msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.logging.Level,
     *      java.util.Collection)
     */
    @Override
    public <T> void log(final Level level, final Collection<T> msg) {
        ChatOut.log(level, msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.Collection)
     */
    @Override
    public <T> void log(final Collection<T> msg) {
        ChatOut.log(msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.logging.Level,
     *      java.lang.Object[])
     */
    @Override
    public <T> void log(final Level level, final T... msg) {
        ChatOut.log(level, msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.lang.Object[])
     */
    @Override
    public <T> void log(final T... msg) {
        ChatOut.log(msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#sendMessage(org.bukkit.command.CommandSender,
     *      java.lang.Object)
     */
    @Override
    public <T> void sendMessage(final CommandSender sender, final T msg) {
        ChatOut.sendMessage(sender, msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#sendMessage(org.bukkit.command.CommandSender,
     *      java.util.Collection)
     */
    @Override
    public <T> void sendMessage(final CommandSender sender, final Collection<T> msg) {
        ChatOut.sendMessage(sender, msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#sendMessage(org.bukkit.command.CommandSender,
     *      java.lang.Object[])
     */
    @Override
    public <T> void sendMessage(final CommandSender sender, final T... msg) {
        ChatOut.sendMessage(sender, msg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getServer()
     */
    @Override
    public IpmServer getServer() {
        return SimpleIpmServer.getInstance();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getConfig()
     */
    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getDataFolder()
     */
    @Override
    public File getDataFolder() {
        return dataFolder;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getDescription()
     */
    @Override
    public IpmPluginDescription getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        if (!event.getPropertyName().equalsIgnoreCase("enabled")) {
            return;
        }

        if (event.getNewValue().equals(true)) {
            System.out.println("[" + getDescription().getName() + "] Enabling " + getDescription().getName() + " v"
                    + getDescription().getVersion());
            onEnable();
        } else if (event.getNewValue().equals(false)) {
            System.out.println("[" + getDescription().getName() + "] Disabling " + getDescription().getName() + " v"
                    + getDescription().getVersion());
            onDisable();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 + (name == null ? 0 : name.hashCode());
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof SimpleIpmPlugin)) {
            return false;
        }

        IpmPlugin that = (SimpleIpmPlugin) obj;

        if (name == null || that.getName() == null) {
            return false;
        }

        return name.equals(that.getName());
    }
}
