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
package com.blockhaus2000.util;

import java.util.Collection;

/**
 * This class provides some static methods for the usage of arrays. See the
 * methods below for the full documentation what the specific method does.
 * 
 * @author Blockhaus2000
 */
public class ArrayUtil {
    /**
     * Converts the given {@link Collection} to an {@link String}[]. To convert
     * the content to a string this will call {@link Object#toString()} or, with
     * the Java dynamic method binding, on the given content types.
     * 
     * @param col
     *            The {@link Collection} that has to be converted to a an
     *            {@link String}[].
     * @return The converted {@link String}[].
     */
    public static <E> String[] toStringArray(final Collection<E> col) {
        String[] array = new String[col.size()];

        int i = 0;
        for (Object target : col.toArray()) {
            array[i++] = target.toString();
        }

        return array;
    }

    /**
     * Removes the given index from the given {@link String}[] and returns the
     * new {@link String}[].
     * 
     * @param array
     *            The {@link String}[] that contains the index to remove.
     * @param index
     *            The index to remove.
     * @return A new {@link String}[] where the given index has been removed.
     * @throws ArrayIndexOutOfBoundsException
     *             Will be thrown if the given index is out of bounds of the
     *             array.
     */
    public static String[] removeFromArray(final String[] array, final int index) throws ArrayIndexOutOfBoundsException {
        String[] edited = new String[array.length - 1];

        boolean removed = false;
        for (int i = 0; i < array.length; i++) {
            if (!removed && i == index) {
                continue;
            } else if (!removed) {
                edited[i] = array[i];
            } else {
                edited[i - 1] = array[i];
            }
        }

        return edited;
    }
}
