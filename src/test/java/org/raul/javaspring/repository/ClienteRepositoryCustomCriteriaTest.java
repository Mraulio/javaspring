package org.raul.javaspring.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.raul.javaspring.entity.Cliente;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ClienteRepositoryCustomCriteriaTest {

    @PersistenceContext
    private EntityManager em;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        em.createQuery("DELETE FROM Cliente").executeUpdate();

        cliente1 = new Cliente();
        cliente1.setNombre("Pedro");
        cliente1.setEmail("pedro@test.com");
        cliente1.setTelefono("123456789");
        cliente1.setDireccion("Calle Uno");

        cliente2 = new Cliente();
        cliente2.setNombre("Luis");
        cliente2.setEmail("luis@test.com");
        cliente2.setTelefono("987654321");
        cliente2.setDireccion("Calle Dos");

        em.persist(cliente1);
        em.persist(cliente2);
        em.flush();
    }

    private List<Cliente> buscarPorFiltrosConCriteria(String nombre, String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> root = cq.from(Cliente.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nombre != null) {
            predicates.add(cb.equal(root.get("nombre"), nombre));
        }
        if (email != null) {
            predicates.add(cb.equal(root.get("email"), email));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Cliente> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Test
    void testBuscarPorNombre() {
        List<Cliente> resultados = buscarPorFiltrosConCriteria("Pedro", null);
        assertEquals(1, resultados.size());
        assertEquals("Pedro", resultados.get(0).getNombre());
    }

    @Test
    void testBuscarPorEmail() {
        List<Cliente> resultados = buscarPorFiltrosConCriteria(null, "luis@test.com");
        assertEquals(1, resultados.size());
        assertEquals("Luis", resultados.get(0).getNombre());
    }

    @Test
    void testBuscarPorNombreYEmail() {
        List<Cliente> resultados = buscarPorFiltrosConCriteria("Pedro", "pedro@test.com");
        assertEquals(1, resultados.size());
        assertEquals("Pedro", resultados.get(0).getNombre());
    }

    @Test
    void testBuscarSinFiltros() {
        List<Cliente> resultados = buscarPorFiltrosConCriteria(null, null);
        assertEquals(2, resultados.size());
    }

    @Test
    void testBuscarNoExiste() {
        List<Cliente> resultados = buscarPorFiltrosConCriteria("NoExiste", "no@existe.com");
        assertTrue(resultados.isEmpty());
    }
}
