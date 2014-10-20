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
package com.blockhaus2000.technical.scheduler.task.runnable;

import com.blockhaus2000.technical.scheduler.task.RepeatingTask;

/**
 * The synchronized version of {@link AsynchRepeatingTaskRunnable}.
 *
 */
public class RepeatingTaskRunnable extends AsynchRepeatingTaskRunnable {
    /**
     * Constructor of RepeatingTaskRunnable.
     *
     * @param task
     *            Is passed into <code>super</code>-call.
     */
    public RepeatingTaskRunnable(final RepeatingTask task) {
        super(task);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.technical.scheduler.task.runnable.AsynchRepeatingTaskRunnable#run()
     */
    @Override
    public synchronized void run() {
        super.run();
    }
}
