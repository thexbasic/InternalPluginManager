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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.minecraft.util.command;

/**
 * 
 * @author Blockhaus2000
 */
public enum CommandPriority {
    LOWEST(0), LOW(1), NORMAL(2), HIGH(3), HIGHEST(4), MONITOR(5);

    private final int level;

    private CommandPriority(final int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
