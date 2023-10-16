package com.xperks.repository;
import com.xperks.persistence.User;
import com.xperks.service.EntityManagerSupport;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
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
}
