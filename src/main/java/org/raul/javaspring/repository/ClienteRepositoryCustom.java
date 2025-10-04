package org.raul.javaspring.repository;

import org.raul.javaspring.entity.Cliente;

import java.util.List;

public interface ClienteRepositoryCustom {
    List<Cliente> buscarPorFiltros(String nombre, String email);
}
