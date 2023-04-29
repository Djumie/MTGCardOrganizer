package br.com.djumie.mtgcardorganizer.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO {
    private final Connection connection;

    public CardDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCard(Cards card) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO cards (number, name, color, rarity, price) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, card.getNumber());
        statement.setString(2, card.getName());
        statement.setString(3, card.getColor());
        statement.setString(4, card.getRarity());
        statement.setDouble(5, card.getPrice());
        statement.executeUpdate();
    }

    public void updateCard(Cards card) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE cards SET number=?, color=?, rarity=?, price=? WHERE name=?");
        statement.setInt(1, card.getNumber());
        statement.setString(2, card.getName());
        statement.setString(3, card.getColor());
        statement.setString(4, card.getRarity());
        statement.setDouble(5, card.getPrice());
        statement.executeUpdate();
    }

    public Cards getCardByName(String name) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM cards WHERE name=?");
        statement.setString(2, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Cards(
                    resultSet.getInt("number"),
                    resultSet.getString("name"),
                    resultSet.getString("color"),
                    resultSet.getString("rarity"),
                    resultSet.getDouble("price")
            );
        } else {
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
