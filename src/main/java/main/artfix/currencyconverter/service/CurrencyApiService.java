package main.artfix.currencyconverter.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CurrencyApiService {

    @Value("${app.currency.api}")
    private String CurrencyAPI;

    public String get(String from, String to, int amount) {
        String getResult = "Error Get Message!";
        try {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(30, TimeUnit.SECONDS);
            client.setReadTimeout(30, TimeUnit.SECONDS);
            client.setWriteTimeout(30, TimeUnit.SECONDS);
            Request request = new Request.Builder()
                    .url("https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "&amount=" + amount)
                    .addHeader("apikey", CurrencyAPI)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String responseBody = response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            boolean success = jsonObject.getBoolean("success");
            if (success) {
                JSONObject query = jsonObject.getJSONObject("query");
                String fromCurrency = query.getString("from");
                String toCurrency = query.getString("to");
                double result = jsonObject.getDouble("result");
                getResult = amount + " " + fromCurrency + " = " + result + " " + toCurrency;
            } else {
                getResult = "Error When Get Currency";
            }
            return getResult;
        }catch (Exception e){
            return getResult;
        }
    }
}
