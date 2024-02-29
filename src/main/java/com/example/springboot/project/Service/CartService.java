package com.example.springboot.project.Service;

import com.example.springboot.project.dto.*;
import com.example.springboot.project.entities.Cart;
import com.example.springboot.project.entities.User;
import com.example.springboot.project.entities.Product;
import com.example.springboot.project.repository.ProductRepository;
import com.example.springboot.project.repository.CartRepository;
import com.example.springboot.project.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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


//    public CartDtoResponse addProductToCartV1(Long cartId, CartDtoRequest cartDtoRequest) {
//        // Validate input DTO
//        if (cartDtoRequest.getProducts() == null || cartDtoRequest.getProductQuantityInCart() <= 0) {
//            // Handle invalid input
//            throw new IllegalArgumentException("Invalid CartDtoRequest");
//        }
//
//        // Fetch Cart entity by cartId
//        Cart cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
//
//            Product product = productRepository.findById(cartDtoRequest.getProducts().get(0).getProductId()).get();
//
//        cart.setProduct(product);
//        // Update cart details
//        cart.setProductQuantityInCart(cart.getProductQuantityInCart() + cartDtoRequest.getProductQuantityInCart());
//        cart.setUpdatedAt(new Date());
//
//        // Save/update the cart
//        cart = cartRepository.save(cart);
//
//        // Map to DTO and return
//        return mapToDTO(cart);
//    }

    public CartDtoResponse addProductToCartV1(Long userId, CartDtoRequest cartDtoRequest) {
        // Validate input DTO
        if (cartDtoRequest.getProducts() == null || cartDtoRequest.getProductQuantityInCart() <= 0) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid CartDtoRequest");
        }

        // Fetch Cart entity by user ID
        //Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
      //  Optional<Cart> optionalCart = cartRepository.findById(userId);

        //Cart cart = optionalCart.orElse(new Cart());


        /*if (cart.getId() == null) {
            // Create a new cart if it doesn't exist
            cart.setId(cartDtoRequest.getId());
            cart.setCreatedAt(new Date()); // Set creation timestamp
        }*/

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = productRepository.findById(cartDtoRequest.getProducts().get(0).getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));



        // Set the user ID in the cart
        Cart cart = new Cart();        //user.setId(userId);
        cart.setUser(user);
        cart.setProduct(product);
        cart.setCreatedAt(new Date());
        cart.setUpdatedAt(new Date());


        cart.setProductQuantityInCart(cart.getProductQuantityInCart() + cartDtoRequest.getProductQuantityInCart());

        // Save/update the cart
        cart = cartRepository.save(cart);

        // Map to DTO and return
        return mapToDTO(cart);
    }


//    public void removeProductFromCart(Long cartId) {
//        // Remove Cart by id
//        cartRepository.deleteById(cartId);
//    }

    public void removeProductFromCart(Long userId, Long cartId) {
        // Find the cart by userId
       // Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        Optional<Cart> optionalCart = cartRepository.findByIdAndUserId(cartId, userId);


        if (optionalCart.isPresent()) {
            // Remove the cart using its ID
            cartRepository.deleteById(cartId);
        } else {
            throw new IllegalArgumentException("Cart not found for the specified user and cart ID");
        }


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

