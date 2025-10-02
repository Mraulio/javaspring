package org.raul.javaspring.mapper;

import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.entity.Cliente;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion()
        );
    }

    public static Cliente toEntity(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());

        return cliente;
    }
}
