package org.raul.javaspring.service;


import org.raul.javaspring.dto.ClienteCreateDTO;
import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.dto.ClienteUpdateDTO;
import org.raul.javaspring.entity.Cliente;
import org.raul.javaspring.exception.ClienteAlreadyExistsException;
import org.raul.javaspring.exception.ClienteNotFoundException;
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
    public ClienteDTO crearCliente(ClienteCreateDTO clienteDTO){
        // Validar que no existe ningún cliente con ese mismo email
        clienteRepository.findByEmail(clienteDTO.getEmail())
                .ifPresent(c -> {
                    throw new ClienteAlreadyExistsException(clienteDTO.getEmail());
                });

        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente saved = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(saved);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteUpdateDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));

        // Se actualizan solo los datos que vienen en el update DTO
        ClienteMapper.updateEntity(dto, cliente);

        Cliente updated = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(updated);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDTO obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return ClienteMapper.toDTO(cliente);
    }
}
