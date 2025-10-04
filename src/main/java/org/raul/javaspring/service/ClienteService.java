package org.raul.javaspring.service;

import org.raul.javaspring.dto.ClienteCreateDTO;
import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.dto.ClienteUpdateDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listarClientes();
    ClienteDTO crearCliente(ClienteCreateDTO clientDTO);

    ClienteDTO actualizarCliente(Long id, ClienteUpdateDTO dto);

    void eliminarCliente(Long id);

    ClienteDTO obtenerClientePorId(Long id);

    List<ClienteDTO> buscarClientes(String nombre, String email, String telefono);
}
