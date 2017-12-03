package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.database.SmoothieDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        RaakaAineDao raakaAineDao = new RaakaAineDao(database);
        SmoothieDao smoothieDao = new SmoothieDao(database);
        
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
    }
}
