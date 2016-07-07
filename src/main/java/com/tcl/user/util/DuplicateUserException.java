package com.tcl.user.util;


public class DuplicateUserException extends RuntimeException {

    /**
	 *
	 */
	private static final long serialVersionUID = -6998968937736574161L;

	public DuplicateUserException() {
        super( "User already exists, An attempt was made to create a user that already exists");
    }
}

