package org.raul.javaspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.raul.javaspring.dto.ClienteCreateDTO;
import org.raul.javaspring.entity.Cliente;
import org.raul.javaspring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();

        Cliente c1 = new Cliente();
        c1.setNombre("Pedro");
        c1.setEmail("pedro@test.com");
        c1.setTelefono("111111111");
        c1.setDireccion("Calle Uno");

        Cliente c2 = new Cliente();
        c2.setNombre("Luis");
        c2.setEmail("luis@test.com");
        c2.setTelefono("222222222");
        c2.setDireccion("Calle Dos");

        clienteRepository.save(c1);
        clienteRepository.save(c2);
    }

    @Test
    void postCliente() throws Exception {
        ClienteCreateDTO dto = new ClienteCreateDTO();
        dto.setNombre("Ana");
        dto.setEmail("ana@test.com");
        dto.setTelefono("123123123");
        dto.setDireccion("Calle XX");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"));
    }

    @Test
    void getClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void buscarClientesPorNombre() throws Exception {
        mockMvc.perform(get("/clientes/buscar")
                        .param("nombre", "Pedro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pedro"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void buscarClientesPorEmail() throws Exception {
        mockMvc.perform(get("/clientes/buscar")
                        .param("email", "luis@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Luis"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void buscarClientesNoEncontrados() throws Exception {
        mockMvc.perform(get("/clientes/buscar")
                        .param("nombre", "NoExiste"))
                .andExpect(status().isNotFound());
    }

}
