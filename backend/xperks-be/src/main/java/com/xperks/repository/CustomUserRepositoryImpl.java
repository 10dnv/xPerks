package com.xperks.repository;
import com.xperks.service.EntityManagerSupport;

public class CustomUserRepositoryImpl extends EntityManagerSupport implements CustomUserRepository{

    @Override
    public int countSuperiorById(int userId) {
        return ((Number) entityManager.createQuery("SELECT COUNT(*) FROM User WHERE superior.id =:id", Long.class).setParameter("id", userId).getSingleResult()).intValue();
    }
}
