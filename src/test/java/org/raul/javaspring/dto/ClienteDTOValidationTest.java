package org.raul.javaspring.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClienteDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ------------------- CreateDTO -------------------
    @Test
    void validarCreateDTO_NombreNoNulo() {
        ClienteCreateDTO dto = new ClienteCreateDTO();
        dto.setNombre(null);  // Nombre obligatorio
        dto.setEmail("test@test.com");
        dto.setTelefono("123456");
        dto.setDireccion("Direccion");

        Set<ConstraintViolation<ClienteCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Debe fallar si el nombre es nulo");
    }

    @Test
    void validarCreateDTO_EmailFormato() {
        ClienteCreateDTO dto = new ClienteCreateDTO();
        dto.setNombre("Luis");
        dto.setEmail("correo_invalido");
        dto.setTelefono("123456");
        dto.setDireccion("Direccion");

        Set<ConstraintViolation<ClienteCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Debe fallar si el email tiene formato incorrecto");
    }

    // ------------------- UpdateDTO -------------------
    @Test
    void validarUpdateDTO_NombreMinMax() {
        ClienteUpdateDTO dto = new ClienteUpdateDTO();
        dto.setNombre("Al");  // Menor de 3 caracteres, debería fallar

        Set<ConstraintViolation<ClienteUpdateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Debe fallar si el nombre es demasiado corto");
    }

    @Test
    void validarUpdateDTO_EmailFormato() {
        ClienteUpdateDTO dto = new ClienteUpdateDTO();
        dto.setEmail("correo_invalido");

        Set<ConstraintViolation<ClienteUpdateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Debe fallar si el email tiene formato incorrecto");
    }

    @Test
    void validarUpdateDTO_CamposNulosPermitidos() {
        ClienteUpdateDTO dto = new ClienteUpdateDTO();  // Todos nulos, debe ser válido
        Set<ConstraintViolation<ClienteUpdateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Todos los campos nulos deben ser válidos en UpdateDTO");
    }
}
