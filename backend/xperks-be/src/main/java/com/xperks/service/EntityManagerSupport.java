package com.xperks.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EntityManagerSupport {

    @PersistenceContext
    protected EntityManager entityManager;
}
