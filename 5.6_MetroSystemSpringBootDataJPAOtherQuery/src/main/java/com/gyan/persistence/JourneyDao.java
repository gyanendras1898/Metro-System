package com.gyan.persistence;

import java.sql.Timestamp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gyan.beans.Journey;
@Repository
public interface JourneyDao extends JpaRepository<Journey, Integer> {
	
	@Query("select exists(select destinationStationNo from Journey where cardId =:id and destinationStationNo in (0))")
	public boolean isJourneyOngoing(@Param("id") int cardId);

	@Query("select boardingStationNo from Journey where cardId =:id order by swipeIn desc limit 1")
	public int getSourceStation(@Param("id") int cardId);

	@Transactional
	@Modifying
	@Query("update Journey set destinationStationNo =:destinationStationNo, swipeOut=:swipeOut, fair =:fair where cardId =:id and destinationStationNo in (0)")
	public int swipeOut(@Param("id") int cardId, @Param("destinationStationNo") int destinationStationNo,
			@Param("swipeOut") Timestamp swipeOut, @Param("fair") double fair);

	@Query("select fair from Journey where cardId =:id order by swipeOut desc limit 1")
	public double getFare(@Param("id") int cardId);
	
	@Query("SELECT TIMESTAMPDIFF(SECOND, swipeIn, swipeOut) FROM Journey WHERE cardId =:id ORDER BY swipeOut DESC LIMIT 1")
	public int getDuration(@Param("id") int cardId);
	
	@Query("select j from Journey j where j.cardId = :cardId and j.destinationStationNo is null order by j.swipeIn desc")
	public Journey findTop1ByCardIdAndDestinationStationNoIsNullOrderBySwipeInDesc(@Param("cardId") int cardId);
	
	@Query("select j from Journey j where j.cardId = :cardId and j.destinationStationNo is not null order by j.swipeOut desc")
	public Journey findTop1ByCardIdAndDestinationStationNoIsNotNullOrderBySwipeOutDesc(@Param("cardId") int cardId);


}
