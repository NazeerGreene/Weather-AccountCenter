package com.example.AccountCenter.data;

import com.example.AccountCenter.models.UnitGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitGroupRepository extends CrudRepository<UnitGroup, Integer> {
}
