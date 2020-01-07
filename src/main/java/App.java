import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import models.hero;
import models.squad;

import com.google.common.collect.Iterables;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    private static String Hero;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main (String[] args){
        staticFileLocation("/public");
        String layout = " templates/layout.hbs";
        ProcessBuilder process = new ProcessBuilder();
        int port;
        if (process.environment().get("PORT") != null){
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        get("/", (request, response) ->{
            System.out.println(Hero.All());
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("hero", Hero.All());
            model.put("template", "templates/home.hbs");
            return new ModelAndView(new HashMap(), "home.hbs");
        },  new HandlebarsTemplateEngine());


        hero model = new hero();
        get("/squadform", (request, response) ->{

            model.put("template", "templates/squadform.hbs");
            return new ModelAndView (model, layout);
        },  new HandlebarsTemplateEngine());

        get("/squad", (request, response) -> {

            String squadname = request.queryParams("squadn ame");
            String squadcause = request.queryParams("squadcause");
            String membernumber = request.queryParams("membernumber");
            model.put("squadname", squadname);
            model.put("squadcause", squadcause);
            model.put("membernumber", membernumber);
            model.put("template", "templates/squadteam.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/heroform", (request, response) -> {

            model.put("template", "templates/heroform.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/hero", (request, response) -> {

            String heroname = request.queryParams("heroname");
            String whichsquad = request.queryParams("whichsquad");
            String heropower = request.queryParams("heropower");
            String heroweakness = request.queryParams("heroweakness");
            String heroage = request.queryParams("heroage");
            model.put("heroname", heroname);
            model.put("whichsquad", whichsquad);
            model.put("heropower", heropower);
            model.put("heroweakness", heroweakness);
            model.put("heroage", heroage);
            model.put("template", "templates/hero.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/herolist", (request, response) -> {

            model.put("template", "templates/listhero.hbs" );
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

    }
}

}