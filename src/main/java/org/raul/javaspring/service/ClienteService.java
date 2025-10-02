package org.raul.javaspring.service;

import org.raul.javaspring.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listarClientes();
    ClienteDTO crearCliente(ClienteDTO clientDTO);
}
