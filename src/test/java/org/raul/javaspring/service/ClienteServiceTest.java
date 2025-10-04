package org.raul.javaspring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.raul.javaspring.dto.ClienteCreateDTO;
import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void limpiar() {
        clienteRepository.deleteAll();
    }

    @Test
    void crearCliente(){
        ClienteCreateDTO dto = new ClienteCreateDTO();
        dto.setNombre("Pedro");
        dto.setEmail("pedro@test.com");
        dto.setTelefono("134679258");
        dto.setDireccion("Calle buena 789");

        ClienteDTO saved = clienteService.crearCliente(dto);
        assertNotNull(saved.getId());
        assertEquals("Pedro", saved.getNombre());
    }

    @Test
    void crearClienteSinNombre(){
        ClienteCreateDTO dto = new ClienteCreateDTO();
        dto.setEmail("error@test.com");
        dto.setTelefono("000000000");
        dto.setDireccion("N/A");

        assertThrows(DataIntegrityViolationException.class, () -> {
            clienteService.crearCliente(dto);
        });
    }
}
