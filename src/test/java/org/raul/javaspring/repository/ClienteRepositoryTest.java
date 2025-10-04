package org.raul.javaspring.repository;

import org.junit.jupiter.api.Test;
import org.raul.javaspring.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void guardarYBuscarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Paco");
        cliente.setEmail("paco@test.com");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle privada 148");

        Cliente saved = clienteRepository.save(cliente);

        assertNotNull(saved.getId());
        assertEquals("Paco", saved.getNombre());
        assertTrue(clienteRepository.findById(saved.getId()).isPresent());
    }
}
