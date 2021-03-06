/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.csdev.ebus.command;

import java.util.List;

/**
 * A marker of a value with child values
 * 
 * @author Christian Sowada - Initial contribution
 *
 */
public interface IEBusNestedValue {

    /**
     * Returns true if the value has child values
     *
     * @return
     */
    public boolean hasChildren();

    /**
     * Returns the list of all children of this value
     *
     * @return
     */
    public List<IEBusValue> getChildren();

}
