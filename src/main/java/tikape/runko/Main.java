package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.database.SmoothieDao;
import tikape.runko.database.SmoothieRaakaAineDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        RaakaAineDao raakaAineDao = new RaakaAineDao(database);
        SmoothieDao smoothieDao = new SmoothieDao(database);
        SmoothieRaakaAineDao smoothieRaakaAineDao = new SmoothieRaakaAineDao(database);
        
        get("/raaka-aineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaaineet", raakaAineDao.findAll());
            
            return new ModelAndView(map, "raaka-aineet");
        }, new ThymeleafTemplateEngine());
        
        post("/raaka-aineet", (req, res) -> {
            String raakaAine = req.queryParams("nimi");
            raakaAineDao.lisaaRaakaAine(raakaAine);
            res.redirect("/raaka-aineet");
            return "";
        });
        
        get("/poista-raaka-aine/:id", (req, res) -> {
            Integer raakaAine = Integer.parseInt(req.params("id"));
            raakaAineDao.delete(raakaAine);
            res.redirect("/raaka-aineet");
            return "";
        });
             
        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("smoothielista", smoothieDao.findAll());
            
            return new ModelAndView(map, "smoothielista");
        }, new ThymeleafTemplateEngine());
                  
        get("/smoothiet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("smoothielista", smoothieDao.findAll());
            map.put("raakaaineet", raakaAineDao.findAll());

            return new ModelAndView(map, "smoothiet");
        }, new ThymeleafTemplateEngine());
        
        post("/smoothiet", (req, res) -> {
            String smoothie = req.queryParams("nimi");
            smoothieDao.lisaaSmoothie(smoothie);
            res.redirect("/smoothiet");
            return "";
        });
        
        get("/poista-smoothie/:id", (req, res) -> {
            Integer smoothie = Integer.parseInt(req.params("id"));
            smoothieDao.delete(smoothie);
            res.redirect("/smoothiet");
            return "";
        });
                
        post("/lisaa-raaka-aine", (req, res) -> {
            Integer smoothieId = Integer.parseInt(req.queryParams("smoothieId"));
            Integer raakaAineId = Integer.parseInt(req.queryParams("raakaAineId"));
            Integer jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
            String maara = req.queryParams("maara");
            String ohje = req.queryParams("ohje");
            smoothieRaakaAineDao.lisaaSmoothieRaakaAine(smoothieId, raakaAineId, jarjestys, maara, ohje);
            res.redirect("/smoothiet");
            return "";
        });
    }
}
