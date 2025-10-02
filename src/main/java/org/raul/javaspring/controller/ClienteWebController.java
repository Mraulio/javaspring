package org.raul.javaspring.controller;

import org.raul.javaspring.entity.Cliente;
import org.raul.javaspring.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
