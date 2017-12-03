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
import java.util.Collections;
import java.util.Comparator;
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
    
    public List<SmoothieRaakaAine> haeResepti(Integer smoothieId) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM SmoothieRaakaAine "
                + "WHERE smoothie_id = ?");
        stmt.setObject(1, smoothieId);

        ResultSet rs = stmt.executeQuery();
        List<SmoothieRaakaAine> ohjeet = new ArrayList<>();
        while (rs.next()) {
            Integer raakaAineId = rs.getInt("raakaaine_id");
            Integer jarjestys = rs.getInt("jarjestys");
            String maara = rs.getString("maara");
            String ohje = rs.getString("ohje");
            ohjeet.add(new SmoothieRaakaAine(smoothieId, raakaAineId, jarjestys, maara, ohje));
        }

        rs.close();
        stmt.close();
        connection.close();
        
        Collections.sort(ohjeet, new Comparator<SmoothieRaakaAine>() {
            @Override
            public int compare(SmoothieRaakaAine o1, SmoothieRaakaAine o2) {
                return o1.getJarjestys() - o2.getJarjestys();
            }
            
        });
        return ohjeet;
    }
}
