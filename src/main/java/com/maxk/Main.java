package com.maxk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        BasketSplitter splitter = new BasketSplitter("src/main/java/resources/config.json");

        List<String> items = Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht");
        Map<String, List<String>> splitResult = splitter.split(items);

        for (Map.Entry<String, List<String>> entry : splitResult.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
