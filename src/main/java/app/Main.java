package app;

import static spark.Spark.*;
import com.google.gson.Gson;
import app.store.*;

public class Main {
    public static void main(String[] args) {

        port(8080);
        Gson gson = new Gson();

        RedisStore.init();
        HazelcastStore.init();
        MongoStore.init();

        get("/nosql-lab-rd", (req, res) ->
                gson.toJson(RedisStore.get(req.queryParams("student_no"))));

        get("/nosql-lab-hz", (req, res) ->
                gson.toJson(HazelcastStore.get(req.queryParams("student_no"))));

        get("/nosql-lab-mon", (req, res) ->
                gson.toJson(MongoStore.get(req.queryParams("student_no"))));

    }
}
