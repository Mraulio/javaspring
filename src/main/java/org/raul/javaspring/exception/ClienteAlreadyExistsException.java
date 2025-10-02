package org.raul.javaspring.exception;

public class ClienteAlreadyExistsException extends RuntimeException{
    public ClienteAlreadyExistsException(String email){
        super("El cliente con email '" + email +"' ya existe.");
    }
}
