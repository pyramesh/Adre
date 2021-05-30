package com.ramesh.resource.ratemanagementservice.repository;

import com.ramesh.resource.ratemanagementservice.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ramesh.Yaleru on 5/30/2021
 */
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}
