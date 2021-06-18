package com.amex.ordering.repository;

import com.amex.ordering.model.CatalogItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

    Map<Long, CatalogItem> catalogStorage = new HashMap<>();

    public CatalogRepositoryImpl() {
        catalogStorage.put(1001l, new CatalogItem(1001l, "Apple", "Fresh Apple", 0.60));
        catalogStorage.put(1002l, new CatalogItem(1002l, "Orange", "Fresh Orange", 0.25));
    }

    @Override
    public CatalogItem getCatalogItemById(long catalogId) {
        return catalogStorage.get(catalogId);
    }
}