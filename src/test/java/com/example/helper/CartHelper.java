package com.example.helper;

import java.util.List;

import com.example.components.InventoryItemComponent;

public class CartHelper {
   
    public double priceOfProductsSelected(List<String> productNames, List<InventoryItemComponent> items){ {
        return items.stream()
        .filter(i -> productNames.contains(i.getTitle()))
        .mapToDouble(i -> i.getPrice())
        .sum();
    }
}
}
