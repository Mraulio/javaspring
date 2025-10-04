package org.raul.javaspring.controller;

import org.raul.javaspring.entity.Cliente;
import org.raul.javaspring.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/clientes")
public class ClienteWebController {

    private final ClienteRepository clienteRepository;

    public ClienteWebController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public String listarClientes(Model model){
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes/listar";
    }

    @GetMapping("/crear")
    public String crearClienteForm(Model model){
        model.addAttribute("cliente", new Cliente());
        return "clientes/crear";
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute Cliente cliente){
        clienteRepository.save(cliente);
        return "redirect:/web/clientes";
    }

    @GetMapping("/buscar")
    public String buscarClientes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String telefono,
            Model model
    ) {
        List<Cliente> clientes;

        if (nombre != null && !nombre.isEmpty()) {
            clientes = clienteRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (email != null && !email.isEmpty()) {
            clientes = clienteRepository.findByEmailContainingIgnoreCase(email);
        } else if (telefono != null && !telefono.isEmpty()) {
            clientes = clienteRepository.findByTelefonoContainingIgnoreCase(telefono);
        } else {
            clientes = clienteRepository.findAll();
        }

        model.addAttribute("clientes", clientes);
        return "clientes/listar";
    }

}
