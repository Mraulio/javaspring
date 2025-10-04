package org.raul.javaspring.mapper;

import org.junit.jupiter.api.Test;
import org.raul.javaspring.dto.ClienteCreateDTO;
import org.raul.javaspring.dto.ClienteDTO;
import org.raul.javaspring.dto.ClienteUpdateDTO;
import org.raul.javaspring.entity.Cliente;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class ClienteMapperTest {

    @Test
    void createDtoToEntityAndBack() {
        // Crear DTO de creación
        ClienteCreateDTO createDTO = new ClienteCreateDTO();
        createDTO.setNombre("Luis");
        createDTO.setEmail("luis@test.com");
        createDTO.setTelefono("111222333");
        createDTO.setDireccion("Calle Mapper");

        // Mapear a entidad
        Cliente entity = ClienteMapper.toEntity(createDTO);
        assertEquals("Luis", entity.getNombre());
        assertEquals("111222333", entity.getTelefono());

        // Mapear a DTO completo
        ClienteDTO mappedDTO = ClienteMapper.toDTO(entity);
        assertEquals("Luis", mappedDTO.getNombre());
        assertEquals("111222333", mappedDTO.getTelefono());
        assertNull(mappedDTO.getId()); // No tiene id porque aún no se ha guardado
    }

    @Test
    void dtoToEntityWithId() {
        // Mapear ClienteDTO a Cliente directamente
        ClienteDTO dto = new ClienteDTO(5L, "Ana", "ana@test.com", "444555666", "Calle DTO");

        Cliente entity = new Cliente();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());

        assertEquals(5L, entity.getId());
        assertEquals("Ana", entity.getNombre());
    }

    @Test
    void updateEntityFromUpdateDTO() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Original");
        cliente.setEmail("original@test.com");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle Vieja");

        ClienteUpdateDTO updateDTO = new ClienteUpdateDTO();
        updateDTO.setNombre("Actualizado");
        updateDTO.setEmail(null); // No actualiza
        updateDTO.setTelefono("987654321");

        ClienteMapper.updateEntity(updateDTO, cliente);

        assertEquals("Actualizado", cliente.getNombre());
        assertEquals("original@test.com", cliente.getEmail()); // sigue igual
        assertEquals("987654321", cliente.getTelefono());
        assertEquals("Calle Vieja", cliente.getDireccion());
    }
}
