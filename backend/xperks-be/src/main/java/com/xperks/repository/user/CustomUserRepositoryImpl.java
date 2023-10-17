package com.xperks.repository.user;
import com.xperks.persistence.User;
import com.xperks.service.EntityManagerSupport;

import java.util.List;

public class CustomUserRepositoryImpl extends EntityManagerSupport implements CustomUserRepository {

    @Override
    public int countSuperiorById(int userId) {
        return ((Number) entityManager
                .createQuery("SELECT COUNT(*) FROM User WHERE superior.id =:id", Long.class)
                .setParameter("id", userId)
                .getSingleResult())
                .intValue();
    }

    @Override
    public List<User> getListWithoutLoggedUser(int id) {
        return entityManager.createQuery("FROM User WHERE id <> :id", User.class)
                .setParameter("id", id)
                .getResultList();
    }
}
