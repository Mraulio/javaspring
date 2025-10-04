package org.raul.javaspring.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.raul.javaspring.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepositoryCustomImpl implements ClienteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cliente> buscarPorFiltros(String nombre, String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = cq.from(Cliente.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nombre != null && !nombre.isEmpty()) {
            predicates.add(cb.like(cliente.get("nombre"), "%" + nombre + "%"));
        }

        if (email != null && !email.isEmpty()) {
            predicates.add(cb.like(cliente.get("email"), "%" + email + "%"));
        }

        cq.select(cliente)
                .where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
