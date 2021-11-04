package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

    @FXML
    private Button usd;

    @FXML
    private Button eur;

    @FXML
    private Button chf;

    @FXML
    private Text rate;

    @FXML
    void initialize() {

       usd.setOnAction(event->{
           DataOfCurrency currency = DbConnection.DbGet("USD");
           if(currency!=null){
               double number=1/currency.rate.doubleValue();
               rate.setText(String.format("%.2f", number)+" рублей");
           }
           else {
               String output = getUrlContent("https://www.cbr-xml-daily.ru/latest.js");
               if (!output.isEmpty()) {
                   JSONObject obj = new JSONObject(output);
                   double number = 1 / obj.getJSONObject("rates").getDouble("USD");
                   rate.setText(String.format("%.2f", number) + " рублей");
                   DbConnection.DbInsert("USD",BigDecimal.valueOf(number));
               }
           }
        });

        eur.setOnAction(event->{
            DataOfCurrency currency = DbConnection.DbGet("EUR");
            if(currency!=null){
                double number=1/currency.rate.doubleValue();
                rate.setText(String.format("%.2f", number)+" рублей");
            }
            else {
                String output = getUrlContent("https://www.cbr-xml-daily.ru/latest.js");
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    double number = 1 / obj.getJSONObject("rates").getDouble("EUR");
                    rate.setText(String.format("%.2f", number) + " рублей");
                    DbConnection.DbInsert("USD",BigDecimal.valueOf(number));
                }
            }
        });

        chf.setOnAction(event->{
            DataOfCurrency currency = DbConnection.DbGet("USD");
            if(currency!=null){
                double number=1/currency.rate.doubleValue();
                rate.setText(String.format("%.2f", number)+" рублей");
            }
            else {
                String output = getUrlContent("https://www.cbr-xml-daily.ru/latest.js");
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    double number = 1 / obj.getJSONObject("rates").getDouble("CHF");
                    rate.setText(String.format("%.2f", number) + " рублей");
                    DbConnection.DbInsert("USD",BigDecimal.valueOf(number));
                }
            }
        });
    }
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn= url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) !=null){
                content.append(line+"\n");
            }
            bufferedReader.close();
        }
        catch (Exception e){
            System.out.println("This exchange doesn't exist");
        }
        return content.toString();
    }



}

