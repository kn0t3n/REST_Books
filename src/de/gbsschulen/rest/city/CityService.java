package de.gbsschulen.rest.city;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CityService() {
        emf = Persistence.createEntityManagerFactory("cities");
        em = emf.createEntityManager();
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    public City getCity(int id) {
        return em.find(City.class, id);
    }

    public List<City> getAllCities() {
        TypedQuery<City> typedQuery = em.createQuery("SELECT b from City b", City.class);
        return typedQuery.getResultList();
    }

    public City deleteCity(int id) {
        City city = getCity(id);
        if (city != null) {
            em.getTransaction().begin();
            em.remove(city);
            em.getTransaction().commit();
        }
        return city;
    }

    public City updateCity(int id, String name) {
        City city = getCity(id);
        if (city != null) {
            em.getTransaction().begin();
            em.createQuery("update table City b set b.name = " + name + " where id = " + id, City.class).executeUpdate();
            em.getTransaction().commit();
        }
        return city;
    }
}
