package com.amex.ordering.repository;

import com.amex.ordering.model.CatalogItem;
import com.amex.ordering.model.Offer;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

    Map<Long, CatalogItem> catalogStorage = new HashMap<>();

    public CatalogRepositoryImpl() {
        Offer offer1 = new Offer(111l, "BXGY", 3, 2);
        Offer offer2 = new Offer(111l, "BXGY", 2, 1);
        catalogStorage.put(1001l, new CatalogItem(1001l, "Apple", "Fresh Apple", 0.60, offer2));
        catalogStorage.put(1002l, new CatalogItem(1002l, "Orange", "Fresh Orange", 0.25, offer1));
    }

    @Override
    public CatalogItem getCatalogItemById(long catalogId) {
        return catalogStorage.get(catalogId);
    }
}