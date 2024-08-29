package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.Product;
import com.rcr.ecommerce.Repository.ProductRepository;
import com.rcr.ecommerce.Request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) throws Exception {
        Product createProduct = new Product();
        createProduct.setDescription(product.getDescription());
        createProduct.setName(product.getName());
        createProduct.setPrice(product.getPrice());
        createProduct.setImages(product.getImages());
        createProduct.setQuantity(product.getQuantity());
        createProduct.setStore(product.getStore());

        Product savedProduct = productRepository.save(createProduct);
        return savedProduct;
    }

    @Override
    public Product updateProduct(UpdateProductRequest product) throws Exception {
        Product updateProduct = findProduct(product.getProductId());

        updateProduct.setQuantity(product.getQuantity());


        Product savedProduct = productRepository.save(updateProduct);
        return savedProduct;
    }

    public Product findProduct(Long productId)throws Exception{
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()){
            throw new Exception();
        }
        return product.get();
    }
    @Override
    public void deleteProduct(Long productId) throws Exception {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getStoreProduct(Long storeId) throws Exception {
        List<Product> products = productRepository.findByStoreId(storeId);

        return products;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = productRepository.findAll();

        return products;
    }
}
