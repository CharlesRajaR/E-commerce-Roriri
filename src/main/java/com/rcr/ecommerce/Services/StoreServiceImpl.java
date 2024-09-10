package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.ProductStore;
import com.rcr.ecommerce.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    private StoreRepository storeRepository;
    @Override
    public ProductStore createStore(ProductStore store) throws Exception {
        ProductStore createStore = new ProductStore();
        createStore.setName(store.getName());
        createStore.setImages(store.getImages());
        createStore.setOwner(store.getOwner());
        createStore.setCreatedAt(store.getCreatedAt());
        createStore.setDescription(store.getDescription());

        ProductStore savedStore = storeRepository.save(createStore);
        return savedStore;
    }

    @Override
    public void deleteStore(Long storeId) throws Exception {
         storeRepository.deleteById(storeId);
    }

    @Override
    public ProductStore getStore(Long storeId) throws Exception {
        ProductStore store = findStore(storeId);
        return store;
    }

    @Override
    public ProductStore getStoreByOwnerId(Long id) throws Exception {
        ProductStore store = storeRepository.findByOwnerId(id);
        return store;
    }

    private ProductStore findStore(Long storeId) throws Exception {
        Optional<ProductStore> store = storeRepository.findById(storeId);
        if(store.isEmpty()){
            throw new Exception("store not found");
        }
        return store.get();
    }

    @Override
    public List<ProductStore> getAllStore() throws Exception {
        List<ProductStore> stores = storeRepository.findAll();

        return stores;
    }
}
