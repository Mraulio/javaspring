package org.raul.javaspring.service;


import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.entity.Cliente;
import org.raul.javaspring.exception.ClienteAlreadyExistsException;
import org.raul.javaspring.mapper.ClienteMapper;
import org.raul.javaspring.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .toList();
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO){
        clienteRepository.findByEmail(clienteDTO.getEmail())
                .ifPresent(c -> {
                    throw new ClienteAlreadyExistsException(clienteDTO.getEmail());
                });

        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente saved = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(saved);
    }
}
