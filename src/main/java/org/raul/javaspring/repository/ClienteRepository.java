package org.raul.javaspring.repository;

import org.raul.javaspring.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryCustom {
    Optional<Cliente> findByEmail(String email);

    // Se generan automaticamente con Spring Data
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    List<Cliente> findByEmailContainingIgnoreCase(String email);
    List<Cliente> findByTelefonoContainingIgnoreCase(String telefono);
}
