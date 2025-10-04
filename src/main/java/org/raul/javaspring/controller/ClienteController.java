package org.raul.javaspring.controller;

import jakarta.validation.Valid;
import org.raul.javaspring.dto.ClienteCreateDTO;
import org.raul.javaspring.dto.ClienteDTO;

import org.raul.javaspring.dto.ClienteUpdateDTO;
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

    @GetMapping("/{id}")
    public ClienteDTO obtenerCliente(@PathVariable Long id){
        return clienteService.obtenerClientePorId(id);
    }

    @GetMapping("/buscar")
    public List<ClienteDTO> buscarClientes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String telefono
    ) {
        return clienteService.buscarClientes(nombre, email, telefono);
    }

    @PostMapping
    public ClienteDTO crearCliente(@Valid @RequestBody ClienteCreateDTO dto) {
        return clienteService.crearCliente(dto);
    }

    @PutMapping("/{id}")
    public ClienteDTO actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteUpdateDTO dto){
        return clienteService.actualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
    }

}
