package com.maxk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BasketSplitterTest {
    private BasketSplitter splitter;

    @BeforeEach
    void setUp() {
        Map<String, List<String>> productDeliveryOptions = new HashMap<>();
        productDeliveryOptions.put("Apple", Arrays.asList("Courier", "Express"));
        productDeliveryOptions.put("Banana", Collections.singletonList("Courier"));
        productDeliveryOptions.put("Chair", Collections.singletonList("FurnitureDelivery"));
        productDeliveryOptions.put("Desk", Arrays.asList("FurnitureDelivery", "Express"));
        splitter = new BasketSplitter(productDeliveryOptions);
    }

    @Test
    void testSplitBasic() {
        List<String> items = Arrays.asList("Apple", "Banana");
        Map<String, List<String>> result = splitter.split(items);

        assertFalse(result.isEmpty(), "The result map should not be empty.");
        assertTrue(result.containsKey("Courier"), "Courier should be a delivery option.");
        assertTrue(result.get("Courier").containsAll(Arrays.asList("Apple", "Banana")), "Both items should be deliverable by Courier.");
    }

    @Test
    void testEmptyItems() {
        List<String> items = Collections.emptyList();
        Map<String, List<String>> result = splitter.split(items);

        assertTrue(result.isEmpty(), "Result should be empty for an empty items list.");
    }

    @Test
    void testItemsWithoutDeliveryOptions() {
        List<String> items = Collections.singletonList("NonDeliveryItem");
        Map<String, List<String>> result = splitter.split(items);

        assertTrue(result.isEmpty(), "Result should be empty for items without delivery options.");
    }

    @Test
    void testAllItemsWithSameDeliveryOption() {
        List<String> items = Arrays.asList("Chair", "Desk");
        Map<String, List<String>> result = splitter.split(items);

        assertEquals(1, result.size(), "All items should be grouped under a single delivery option.");
        assertTrue(result.containsKey("FurnitureDelivery"), "Items should be deliverable by FurnitureDelivery.");
    }

    @Test
    void testOptimalDeliveryGroupSplit() {
        List<String> items = Arrays.asList("Apple", "Desk");
        Map<String, List<String>> result = splitter.split(items);

        assertEquals(1, result.keySet().size(), "Items should be grouped under the most optimal delivery option.");
        assertTrue(result.get("Express").containsAll(Arrays.asList("Apple", "Desk")), "Both items should be deliverable by Express.");
    }

    @Test
    void testSplitWithMaximumNumberOfItems() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add("Product " + i);
        }
        Map<String, List<String>> result = splitter.split(items);

        assertNotNull(result, "The result should not be null even with the maximum number of items.");
    }

    @Test
    void testSplitWithDuplicateItems() {
        List<String> items = Collections.nCopies(10, "Apple");
        Map<String, List<String>> result = splitter.split(items);

        assertEquals(1, result.size(), "There should be one delivery group for duplicate items.");
        assertTrue(result.values().stream().anyMatch(list -> list.contains("Apple") && list.size() == 10),
                "The 'Apple' should be present 10 times in one of the delivery groups.");
    }

    @Test
    void testSplitWithMultipleDeliveryOptions() {
        Map<String, List<String>> result = splitter.split(Arrays.asList("Apple", "Banana", "Chair", "Desk"));

        assertTrue(result.containsKey("Courier") && result.containsKey("FurnitureDelivery"),
                "All delivery options should be represented in the result.");
    }
}