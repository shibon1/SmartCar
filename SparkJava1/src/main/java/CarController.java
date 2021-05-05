
import com.google.gson.Gson;
import com.smartcar.sdk.AuthClient;
import com.smartcar.sdk.data.Auth;
import java.io.PrintWriter;
import static spark.Spark.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author enta
 */
public class CarController {

    private static Gson gson = new Gson();

    public CarController() {
    }

    public void getAccess(AuthClient client) {

        get("/login", (req, res) -> {
            AuthClient.AuthUrlBuilder link = client.authUrlBuilder();
            res.redirect(link.build());
            return null;
        });

        get("/callback", (req, res) -> {
            String code = req.queryMap("code").value();

            Auth auth = client.exchangeCode(code);

            // in a production app you'll want to store this in some kind of persistent storage
            String access = auth.getAccessToken();

            PrintWriter writer = new PrintWriter("C:\\Users\\Shiboni\\AppData\\Local\\Temp\\access");
            writer.println(access);
            writer.close();

            return access;
        });
    }
}
