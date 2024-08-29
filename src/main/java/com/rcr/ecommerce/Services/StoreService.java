package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.ProductStore;

import java.util.List;

public interface StoreService {
    public ProductStore createStore(ProductStore store)throws Exception;

    public void deleteStore(Long storeId)throws Exception;

    public ProductStore getStore(Long storeId)throws Exception;
    public List<ProductStore> getAllStore()throws Exception;
}
