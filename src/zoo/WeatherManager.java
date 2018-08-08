package zoo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import zoo.Controllers.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;

public class WeatherManager extends Thread {

    private ArrayList<Object> getWeather() throws Exception{
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=London&APPID=082f4c1bdc5c2727537248c1e040d944&units=metric");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            LocalTime localTime = LocalTime.now();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            double temp = new Gson().fromJson(content.toString(), JsonObject.class).getAsJsonObject("main").get("temp").getAsDouble();

            return new ArrayList<>(){{add(temp);add(localTime);}};
    }
    public void run(Controller controller) throws Exception{
        controller.setWeather(getWeather());
    }
}
