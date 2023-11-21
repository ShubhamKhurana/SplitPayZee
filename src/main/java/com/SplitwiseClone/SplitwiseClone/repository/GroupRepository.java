package com.SplitwiseClone.SplitwiseClone.repository;

import com.SplitwiseClone.SplitwiseClone.model.Group;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
