/**
 *   Copyright 2015 The CHOReVOLUTION project
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.zefxis.dexms.mediator.manager.setinvaddrservice;

import java.util.List;

public interface BaseService {

    public final static String namespace="http://services.chorevolution.eu/";
    
    /* (non-Javadoc)
     * @see org.choreos.services.Base#setInvocationAddress(java.lang.String, java.lang.String)
     */
    void setInvocationAddress(String role, String name, List<String> endpoints);

}