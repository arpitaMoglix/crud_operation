package com.example.springboot.project.Service;

import com.example.springboot.project.dto.*;
import com.example.springboot.project.entities.Cart;
import com.example.springboot.project.entities.Product;
import com.example.springboot.project.repository.ProductRepository;
import com.example.springboot.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService implements CartServiceInterface{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private productService productService;

    public CartDtoResponse getCartById(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return mapToDTO(cart);
        } else {
            throw new RuntimeException("Cart not found with id: " + cartId);
        }
    }

    public Long createCart() {
        Cart cart = new Cart();
        Date now = new Date();
        cart.setCreatedAt(now);
        cart.setUpdatedAt(now);
        // Set creation timestamp
        Cart savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }


    public CartDtoResponse addProductToCartV1(Long cartId, CartDtoRequest cartDtoRequest) {
        // Validate input DTO
        if (cartDtoRequest.getProducts() == null || cartDtoRequest.getProductQuantityInCart() <= 0) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid CartDtoRequest");
        }

        // Fetch Cart entity by cartId
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

            Product product = productRepository.findById(cartDtoRequest.getProducts().get(0).getProductId()).get();

        cart.setProduct(product);
        // Update cart details
        cart.setProductQuantityInCart(cart.getProductQuantityInCart() + cartDtoRequest.getProductQuantityInCart());
        cart.setUpdatedAt(new Date());

        // Save/update the cart
        cart = cartRepository.save(cart);

        // Map to DTO and return
        return mapToDTO(cart);
    }

    public void removeProductFromCart(Long cartId) {
        // Remove Cart by id
        cartRepository.deleteById(cartId);
    }

    private Product mapToProductEntity(ProductDtoWithoutCategory productDto) {
        // Map ProductDTO to Product entity
        Product product = new Product();
        product.setId(productDto.getProductId());
        product.setName(productDto.getProductName());
        product.setPrice(productDto.getProductPrice());
        product.setDescription(productDto.getProductDescription());

        return product;
    }

    private CartDtoResponse mapToDTO(Cart cart) {
        // Map Cart entity to CartDtoResponse
        CartDtoResponse cartDtoResponse = new CartDtoResponse();
        cartDtoResponse.setId(cart.getId());
        cartDtoResponse.setProductQuantityInCart(cart.getProductQuantityInCart());
        cartDtoResponse.setCreatedAt(cart.getCreatedAt());
        cartDtoResponse.setUpdatedAt(cart.getUpdatedAt());

        ProductDtoWithoutCategory productDto = mapToProductDtoWithoutCategory(cart.getProduct());

        cartDtoResponse.setProducts(Collections.singletonList(productDto));

        return cartDtoResponse;
    }



    private ProductDtoWithoutCategory mapToProductDtoWithoutCategory(Product product) {
        ProductDtoWithoutCategory productDtoWithoutCategory = new ProductDtoWithoutCategory();
        productDtoWithoutCategory.setProductId(product.getId());
        productDtoWithoutCategory.setProductName(product.getName());
        productDtoWithoutCategory.setProductPrice(product.getPrice());
        productDtoWithoutCategory.setProductDescription(product.getDescription());

        return productDtoWithoutCategory;
    }

    private ProductDtoWithoutCategory mapToProductDtoWithoutCategory(ProductDTO productDTO) {
        ProductDtoWithoutCategory productDtoWithoutCategory = new ProductDtoWithoutCategory();
        productDtoWithoutCategory.setProductId(productDTO.getProductId());
        productDtoWithoutCategory.setProductName(productDTO.getProductName());
        productDtoWithoutCategory.setProductPrice(productDTO.getProductPrice());
        productDtoWithoutCategory.setProductDescription(productDTO.getProductDescription());

        return productDtoWithoutCategory;
    }

}

