/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.RaakaAine;
import tikape.runko.domain.Smoothie;

public class SmoothieDao implements Dao<Smoothie, Integer> {

    private final Database database;

    public SmoothieDao(Database database) {
        this.database = database;
    }

    @Override
    public Smoothie findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Smoothie WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Smoothie s = new Smoothie(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return s;
    }

    @Override
    public List<Smoothie> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Smoothie");

        ResultSet rs = stmt.executeQuery();
        List<Smoothie> smoothiet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            smoothiet.add(new Smoothie(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return smoothiet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Smoothie "
                + "WHERE id = ?");
        stmt.setInt(1, key);

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void lisaaSmoothie(String nimi) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Smoothie"
                + " (nimi) VALUES (?)");
        stmt.setString(1, nimi);

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
}
