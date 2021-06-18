package com.amex.ordering.repository;

import com.amex.ordering.model.CatalogItem;

public interface CatalogRepository {

    CatalogItem getCatalogItemById(long catalogId);
}
