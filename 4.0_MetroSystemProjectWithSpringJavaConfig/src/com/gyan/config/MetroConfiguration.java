package com.gyan.config;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.gyan.exceptions.InvalidInputException;
import com.gyan.persistence.CardDao;
import com.gyan.persistence.CardDaoImpl;
import com.gyan.persistence.JourneyDao;
import com.gyan.persistence.JourneyDaoImpl;
import com.gyan.persistence.StationDao;
import com.gyan.persistence.StationDaoImpl;
import com.gyan.presentation.MetroPresentation;
import com.gyan.presentation.MetroPresentationImpl;
import com.gyan.presentation.UserPresentation;
import com.gyan.presentation.UserPresentationImpl;
import com.gyan.service.CardService;
import com.gyan.service.CardServiceImpl;
import com.gyan.service.JourneyService;
import com.gyan.service.JourneyServiceImpl;
import com.gyan.service.StationService;
import com.gyan.service.StationServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.gyan")
public class MetroConfiguration {
	
	@Bean
	public CardDao cardDao() {
		return new CardDaoImpl();
	}
	@Bean
	public JourneyDao journeyDao() {
		return new JourneyDaoImpl();
	}
	@Bean
	public StationDao stationDao() {
		return new StationDaoImpl();
	}
	@Bean
	public CardService cardService() {
		return new CardServiceImpl();
	}
	@Bean
	public JourneyService journeyService() {
		return new JourneyServiceImpl();
	}
	@Bean
	public StationService stationService() {
		return new StationServiceImpl();
	}
	@Bean
	public MetroPresentation metroPresentation() {
		return new MetroPresentationImpl();
	}
	@Bean
	public UserPresentation userPresentation() {
		return new UserPresentationImpl();
	}
	@Bean
	public InvalidInputException invalidInputException() {
		return new InvalidInputException("Warning!!! Please input integer only");
	}
	@Bean
	public DataSource dataSource() {
		String url = "jdbc:mysql://localhost:3306/metro_system_db";
		String username = "root";
		String password = "admin";
		DataSource dataSource = new DriverManagerDataSource(url, username, password);
		return dataSource;
	}
}
