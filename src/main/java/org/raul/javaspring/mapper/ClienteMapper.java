package org.raul.javaspring.mapper;

import org.raul.javaspring.dto.ClienteCreateDTO;
import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.dto.ClienteUpdateDTO;
import org.raul.javaspring.entity.Cliente;

public class ClienteMapper {

    // -------- Entidad -> DTO --------
    public static ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion()
        );
    }

    // -------- DTO -> Entidad --------

    /**
     * Para crear un cliente
     * @param dto
     * @return
     */
    public static Cliente toEntity(ClienteCreateDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());

        return cliente;
    }

    /**
     * Para actualizar
     * @param dto
     * @param cliente
     */
    public static void updateEntity(ClienteUpdateDTO dto, Cliente cliente){
        if (dto.getNombre() != null) cliente.setNombre(dto.getNombre());
        if (dto.getEmail() != null) cliente.setEmail(dto.getEmail());
        if (dto.getTelefono() != null) cliente.setTelefono(dto.getTelefono());
        if (dto.getDireccion() != null) cliente.setDireccion(dto.getDireccion());
    }
}
