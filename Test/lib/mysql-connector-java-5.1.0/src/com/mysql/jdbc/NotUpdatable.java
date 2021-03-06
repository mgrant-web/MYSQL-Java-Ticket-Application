/*
 Copyright (C) 2002-2004 MySQL AB

 This program is free software; you can redistribute it and/or modify
 it under the terms of version 2 of the GNU General Public License as 
 published by the Free Software Foundation.

 There are special exceptions to the terms and conditions of the GPL 
 as it is applied to this software. View the full text of the 
 exception in file EXCEPTIONS-CONNECTOR-J in the directory of this 
 software distribution.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA



 */
package com.mysql.jdbc;

import java.sql.SQLException;

/**
 * Thrown when a result sate is not updatable
 * 
 * @author Mark Matthews
 */
public class NotUpdatable extends SQLException {
	// ~ Static fields/initializers
	// ---------------------------------------------

	/**
	 * The message to use when result set is not updatable.
	 * 
	 * The same message is used in the warnings generated by Updatabale result
	 * set.
	 */
	public static final String NOT_UPDATEABLE_MESSAGE = Messages
			.getString("NotUpdatable.0") //$NON-NLS-1$
			+ Messages.getString("NotUpdatable.1") //$NON-NLS-1$
			+ Messages.getString("NotUpdatable.2") //$NON-NLS-1$
			+ Messages.getString("NotUpdatable.3") //$NON-NLS-1$
			+ Messages.getString("NotUpdatable.4") //$NON-NLS-1$
			+ Messages.getString("NotUpdatable.5"); //$NON-NLS-1$

	// ~ Constructors
	// -----------------------------------------------------------

	/**
	 * Creates a new NotUpdatable exception.
	 */
	public NotUpdatable() {
		super(NOT_UPDATEABLE_MESSAGE, SQLError.SQL_STATE_GENERAL_ERROR);
	}
}
