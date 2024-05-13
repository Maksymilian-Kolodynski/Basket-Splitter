package com.maxk;

import java.io.IOException;
import java.util.*;

public class BasketSplitter {
    private Map<String, List<String>> productDeliveryOptions;

    public BasketSplitter(String configFilePath) {
        try {
            this.productDeliveryOptions = ConfigParser.parseConfigFile(configFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            this.productDeliveryOptions = new HashMap<>();
        }
    }

    //Constructor for unittests
    public BasketSplitter(Map<String, List<String>> productDeliveryOptions) {
        this.productDeliveryOptions = productDeliveryOptions;
    }

    public Map<String, List<String>> split(List<String> items) {
        Map<String, List<String>> optimalDeliveryGroups = new HashMap<>();
        List<String> remainingItems = new ArrayList<>(items);

        while (!remainingItems.isEmpty()) {

            Map<String, List<String>> groups = createGroups(remainingItems);
            Map<String, List<String>> largestGroupMap = findLargestListMap(groups);

            if (!largestGroupMap.isEmpty()) {
                Map.Entry<String, List<String>> largestGroupEntry = largestGroupMap.entrySet().iterator().next();
                String largestGroupKey = largestGroupEntry.getKey();
                List<String> largestGroupItems = largestGroupEntry.getValue();

                optimalDeliveryGroups.put(largestGroupKey, new ArrayList<>(largestGroupItems));

                remainingItems.removeAll(largestGroupItems);
            } else {
                break;
            }
        }
        return optimalDeliveryGroups;
    }

    private Map<String, List<String>> createGroups(List<String> items) {
        Map<String, List<String>> deliveryGroups = new HashMap<>();

        for (String item : items) {
            List<String> deliveryOptions = productDeliveryOptions.get(item);

            if (deliveryOptions != null) {
                for (String deliveryOption : deliveryOptions) {
                    deliveryGroups.computeIfAbsent(deliveryOption, k -> new ArrayList<>()).add(item);
                }
            }
        }

        return deliveryGroups;
    }

    private Map<String, List<String>> findLargestListMap(Map<String, List<String>> map) {
        if (map == null || map.isEmpty()) {
            return Collections.emptyMap();
        }

        Optional<Map.Entry<String, List<String>>> largestEntry = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue((list1, list2) -> Integer.compare(list1.size(), list2.size())));

        Map<String, List<String>> result = new HashMap<>();
        largestEntry.ifPresent(entry -> result.put(entry.getKey(), entry.getValue()));

        return result;
    }


}