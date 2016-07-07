package com.tcl.user.util;


/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super( "User Not Found , No User could be found for that Id");
    }
}
