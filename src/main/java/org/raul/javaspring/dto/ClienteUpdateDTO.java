package org.raul.javaspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteUpdateDTO {

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Email(message = "El email debe tener un formato válido")
    private String email;

    @Size(max = 15, message = "El teléfono no puede superar los 15 caracteres")
    private String telefono;

    private String direccion;
}
