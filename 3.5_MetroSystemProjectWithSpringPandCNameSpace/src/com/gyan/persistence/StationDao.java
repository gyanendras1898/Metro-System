package com.gyan.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyan.beans.Station;

public interface StationDao {

	List<Station> getStations() throws ClassNotFoundException, SQLException;

}
