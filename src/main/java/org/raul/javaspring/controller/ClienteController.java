package org.raul.javaspring.controller;

import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.entity.Cliente;
import org.raul.javaspring.mapper.ClienteMapper;
import org.raul.javaspring.repository.ClienteRepository;
import org.raul.javaspring.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    @PostMapping
    public ClienteDTO crearCliente(@RequestBody ClienteDTO dto) {
        return clienteService.crearCliente(dto);
    }

}
