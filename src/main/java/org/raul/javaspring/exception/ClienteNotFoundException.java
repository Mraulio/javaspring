package org.raul.javaspring.exception;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(Long id){
        super("Cliente con id " + id + " no encontrado.");
    }

    public ClienteNotFoundException(String message){
        super(message);
    }
}
