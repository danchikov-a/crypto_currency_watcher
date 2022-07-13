package com.idfinance.service;

import com.idfinance.model.Coin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoinService {
    private static final String SERVER_CONFIG_PATH = "src/main/resources/server.json";
    private static final String ID_KEY = "id";
    private static final String SYMBOL_KEY = "symbol";
    private List<Coin> coins;

    public CoinService() {
        coins = new ArrayList<>();
        parseServerConfig();
    }

    public void parseServerConfig() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray;

        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(SERVER_CONFIG_PATH));
        } catch (IOException | ParseException exception) {
            throw new RuntimeException(exception);
        }

        jsonArray.forEach(jsonObject -> {
                    JSONObject coin = (JSONObject) jsonObject;
                    Long coinId = Long.parseLong((String) coin.get(ID_KEY));
                    String coinName = (String) coin.get(SYMBOL_KEY);

                    coins.add(new Coin(coinId, coinName));
                });
    }

    public List<Coin> getCoinList() {
        return coins;
    }
}
