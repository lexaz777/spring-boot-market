package com.geekbrains.springbootproject.soap;

import com.geekbrains.springbootproject.entities.Product;
import com.geekbrains.springbootproject.repositories.ProductsRepository;
import com.geekbrains.springsoap.gen.GetProductsRequest;
import com.geekbrains.springsoap.gen.GetProductsResponse;
import com.geekbrains.springsoap.gen.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/springsoap/gen";

    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) throws DatatypeConfigurationException {
        double min = request.getMin();
        double max = request.getMax();

        ArrayList<Product> productList = productsRepository.findAllByPriceBetween(min, max);

        GetProductsResponse response = new GetProductsResponse();

        for (int i = 0; i < productList.size(); i++) {
            ProductWrapper productwrapper = new ProductWrapper();
            productwrapper.setTitle(productList.get(i).getTitle());
            productwrapper.setPrice(productList.get(i).getPrice());
            response.getProductWrapper().add(productwrapper);
        }

        return response;
    }
}
