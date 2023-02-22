package com.gyan.model.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gyan.beans.Journey;

@Repository
public interface JourneyDao extends JpaRepository<Journey, Integer> {
	
	@Query("SELECT CASE WHEN COUNT(j) > 0 THEN true ELSE false END FROM Journey j WHERE j.cardId = :id AND j.destinationStationNo = 0")
	boolean isJourneyOngoing(@Param("id") int cardId);


//	@Query(value="SELECT j.boardingStationNo FROM Journey j WHERE j.cardId = :id ORDER BY j.swipeIn DESC LIMIT 1", nativeQuery = true)
//	int getSourceStation(@Param("id") int cardId);
	
	@Query("SELECT j.boardingStationNo FROM Journey j WHERE j.cardId = :id ORDER BY j.swipeIn DESC")
	List<Integer> getSourceStation(@Param("id") int cardId, Pageable page);


//
	@Transactional
	@Modifying
	@Query("update Journey set destinationStationNo =:destinationStationNo, swipeOut=:swipeOut, fair =:fair where cardId =:id and destinationStationNo in (0)")
	public int swipeOut(@Param("id") int cardId, @Param("destinationStationNo") int destinationStationNo,
			@Param("swipeOut") Timestamp swipeOut, @Param("fair") double fair);
//
//	@Query("SELECT j.fare FROM Journey j WHERE j.cardId = :cardId ORDER BY j.swipeOut DESC")
//	Double findMostRecentFareByCardId(@Param("cardId") int cardId, Pageable pageable);
	
	@Query("select fair from Journey where cardId =:id order by swipeOut desc ")
	List<Double> findFareByCardId(@Param("id") int cardId, Pageable page);

	


	
	@Query("SELECT CAST(TIMESTAMPDIFF(SECOND, j.swipeIn, j.swipeOut) AS long) FROM Journey j WHERE j.cardId = :id ORDER BY j.swipeOut DESC")
	List<Long> findJourneyDurationByCardId(@Param("id") int cardId, Pageable page);



	
	@Query("select j from Journey j where j.cardId = :cardId and j.destinationStationNo is null order by j.swipeIn desc")
	List<Journey> findByCardIdAndDestinationStationNoIsNullOrderBySwipeInDesc(@Param("cardId") int cardId, Pageable page);
	
	@Query("select j from Journey j where j.cardId = :cardId and j.destinationStationNo is not null order by j.swipeOut desc")
	List<Journey> findByCardIdAndDestinationStationNoIsNotNullOrderBySwipeOutDesc(@Param("cardId") int cardId, Pageable page);


}
