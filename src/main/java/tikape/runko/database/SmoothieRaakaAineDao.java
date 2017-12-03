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
import tikape.runko.domain.SmoothieRaakaAine;

public class SmoothieRaakaAineDao {

    private final Database database;

    public SmoothieRaakaAineDao(Database database) {
        this.database = database;
    }   

    public void lisaaSmoothieRaakaAine(Integer smoothieId, Integer raakaAineId,
            Integer jarjestys, String maara, String ohje) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO SmoothieRaakaAine"
                + " (smoothie_id, raakaaine_id, jarjestys, maara, ohje) VALUES (?, ?, ?, ?, ?)");
        
        stmt.setInt(1, smoothieId);
        stmt.setInt(2, raakaAineId);
        stmt.setInt(3, jarjestys);
        stmt.setString(4, maara);
        stmt.setString(5, ohje);

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
}
