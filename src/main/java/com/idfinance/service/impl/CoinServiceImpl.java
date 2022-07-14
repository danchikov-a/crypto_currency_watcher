package com.idfinance.service.impl;

import com.idfinance.exception.ServerConfigDoesntExistsException;
import com.idfinance.model.Coin;
import com.idfinance.repository.CoinRepository;
import com.idfinance.service.CoinService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoinServiceImpl implements CoinService {
    private static final String SERVER_CONFIG_PATH = "src/main/resources/server.json";
    private static final String ID_KEY = "id";
    private static final String SYMBOL_KEY = "symbol";
    private static final int FIRST_ELEMENT = 0;
    private static final String PRICE_KEY = "price_usd";
    private static final String API_REQUEST = "https://api.coinlore.net/api/ticker/?id=";
    private List<Coin> coins;
    private CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
        coins = new ArrayList<>();
        parseServerConfig();
    }

    private void parseServerConfig() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray;

        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(SERVER_CONFIG_PATH));
        } catch (IOException | ParseException exception) {
            throw new ServerConfigDoesntExistsException();
        }

        jsonArray.forEach(jsonObject -> {
            JSONObject coin = (JSONObject) jsonObject;
            Long coinId = Long.parseLong((String) coin.get(ID_KEY));
            String coinName = (String) coin.get(SYMBOL_KEY);
            Coin coinToAdd = new Coin(coinId, coinName);

            coins.add(coinToAdd);
        });
    }

    @Override
    public List<Coin> getCoinList() {
        return coins;
    }

    @Override
    public void saveCoin(int coinNumber) {
        String fullRequest = API_REQUEST + coins.get(coinNumber).getId();
        JSONArray jsonArray = new RestTemplate().getForObject(fullRequest, JSONArray.class);

        Map<String, String> map = (LinkedHashMap<String, String>) jsonArray.get(FIRST_ELEMENT);
        Long coinId = Long.parseLong(map.get(ID_KEY));
        String coinSymbol = map.get(SYMBOL_KEY);
        BigDecimal coinPrice = new BigDecimal(map.get(PRICE_KEY));
        Coin coinToSave = new Coin(coinId, coinSymbol, coinPrice);

        coinRepository.save(coinToSave);
    }
}
