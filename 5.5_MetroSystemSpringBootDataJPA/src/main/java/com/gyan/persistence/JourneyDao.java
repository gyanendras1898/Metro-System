package com.gyan.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyan.beans.Journey;
@Repository
public interface JourneyDao extends JpaRepository<Journey, Integer> {

}
