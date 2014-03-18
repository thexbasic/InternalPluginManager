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
package com.blockhaus2000.util.command;

import com.blockhaus2000.plugin.IpmPlugin;

/**
 * An implementation of {@link SingleCommand} for a self-registering command.
 * 
 * @author Blockhaus2000
 */
public abstract class SimpleSingleCommand implements SingleCommand {
    protected final IpmPlugin plugin;

    protected SimpleSingleCommand(final IpmPlugin plugin) {
        this.plugin = plugin;
        this.plugin.registerCommands(this);
    }
}
