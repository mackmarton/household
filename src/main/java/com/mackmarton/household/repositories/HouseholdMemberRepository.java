package com.mackmarton.household.repositories;

import com.mackmarton.household.entities.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Integer> { }
