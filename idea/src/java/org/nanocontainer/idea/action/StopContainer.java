/*****************************************************************************
 * Copyright (C) NanoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 * Original code by                                                          *
 *****************************************************************************/
package org.nanocontainer.idea.action;

import org.nanocontainer.swing.action.StopContainerAction;

/**
 * @author Aslak Helles&oslash;y
 * @version $Revision$
 */
public class StopContainer extends SwingActionAdapter {
    public StopContainer(StopContainerAction delegate) {
        super(delegate);
    }
}
