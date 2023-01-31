package com.gyan.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gyan.beans.Card;

@Repository
public class CardDaoImpl implements CardDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addCard(double balance) throws SQLException {
		String query = "insert into cards (balance) values (?);";
		int affectedRows = jdbcTemplate.update(query, balance);
		return affectedRows>0;
	}

	@Override
	public double viewBalance(int cardId) throws SQLException {
		String query = "select balance from cards where id=?";
		return jdbcTemplate.queryForObject(query, Integer.class, cardId);

	}

	@Override
	public boolean addBalance(int cardId, double balance) throws SQLException {
		String query = "update cards set balance = ? where id=?";
		int affectedRows = jdbcTemplate.update(query, balance, cardId);
		return affectedRows > 0;
	}

	@Override
	public boolean isCardPresent(int cardId) throws SQLException {
		String query = "SELECT EXISTS (SELECT * FROM CARDS WHERE ID=?)";
		int rs = jdbcTemplate.queryForObject(query, Integer.class, cardId);
		return rs == 1;
	}

	@Override
	public List<Card> allCard() throws SQLException {
		List<Card> cards = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		String query = "select * from cards";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			double balance = rs.getDouble("balance");
			cards.add(new Card(id, balance));
		}
		return cards;

	}

	@Override
	public int lastCardAdded() throws SQLException {
		String query = "SELECT ID FROM CARDS ORDER BY ID DESC LIMIT 1;";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

}
