package com.gyan.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyan.beans.Station;
@Repository
public interface StationDao extends JpaRepository<Station, Integer> {

}
