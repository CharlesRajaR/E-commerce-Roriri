package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.Product;
import com.rcr.ecommerce.Request.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product)throws Exception;

    public Product updateProduct(UpdateProductRequest product)throws Exception;

    public void deleteProduct(Long productId)throws Exception;

    public List<Product> getStoreProduct(Long storeId)throws Exception;

    public List<Product> getAllProducts()throws Exception;
    public Product findProduct(Long productId)throws Exception;
}
