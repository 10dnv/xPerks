package com.xperks.repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomUserRepositoryImpl implements  CustomUserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int countSuperiorById(int userId) {
        return ((Number) entityManager.createQuery("SELECT COUNT(*) FROM User WHERE superior.id =:id", Long.class).setParameter("id", userId).getSingleResult()).intValue();
    }
}
