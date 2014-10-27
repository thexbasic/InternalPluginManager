/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.minecraft.block.meta;

/**
 * The {@link RawBlockMeta} contains basic information about a block that does
 * not depent on a "real" block.
 *
 */
public interface RawBlockMeta {
    /**
     *
     * @return The lightlevel of the light that is sent by this block.
     */
    int getLightLevel();

    /**
     *
     * @return The explosion resistance of this block.
     */
    double getExplosionResistance();

    /**
     *
     * @return The severity of this block.
     */
    double getSeverity();

    /**
     *
     * @return Whether this block has gravity or not.
     */
    boolean hasGravity();

    /**
     *
     * @return Whether this block is solid or not.
     */
    boolean isSolid();

    /**
     *
     * @return Whether this block is transparent or not.
     */
    boolean isTransparent();
}