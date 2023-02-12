package com.gyan.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyan.beans.Card;

@Repository
public interface CardDao extends JpaRepository<Card, Integer> {

}
