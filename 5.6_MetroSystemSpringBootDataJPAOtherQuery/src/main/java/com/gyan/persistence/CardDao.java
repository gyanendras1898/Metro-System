package com.gyan.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gyan.beans.Card;


@Repository
public interface CardDao extends JpaRepository<Card, Integer> {

	@Transactional
	@Modifying
	@Query("update Card set balance =:bal where id=:id")
	public Integer addBalance(@Param("id") int cardId,@Param("bal") double balance);

}
