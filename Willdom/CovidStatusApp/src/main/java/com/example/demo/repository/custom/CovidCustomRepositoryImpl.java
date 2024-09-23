package com.example.demo.repository.custom;

import com.example.demo.model.Covid;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class CovidCustomRepositoryImpl implements CovidCustomRepository{

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Integer totalBy(String by) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
    Root<Covid> root = cq.from(Covid.class);

    cq.select(cb.sum(root.get(by)));

    TypedQuery<Integer> query = entityManager.createQuery(cq);
    return query.getSingleResult();
  }
}
