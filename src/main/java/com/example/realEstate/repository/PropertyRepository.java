package com.example.realEstate.repository;

import com.example.realEstate.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query(value = "SELECT * FROM Property p WHERE p.owner_id = :ownerId", nativeQuery = true)
    List<Property> findByOwner(@Param("ownerId") Long ownerId);
}
