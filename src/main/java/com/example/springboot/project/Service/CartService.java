package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CartDTO;
import com.example.springboot.project.dto.CartItemDtoResponse;
import com.example.springboot.project.dto.ProductDTO;
import com.example.springboot.project.entities.Cart;
import com.example.springboot.project.entities.CartItem;
import com.example.springboot.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    private CartRepository cartRepository;

    public CartDTO getCartById(Long cartId) {
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
        Cart savedCart = cartRepository.save(cart);
        return savedCart.getCartId();
    }

    public void clearCart(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getCartItems().clear();
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found with id: " + cartId);
        }
    }



    private CartDTO mapToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setCartItems(cart.getCartItems().stream().map(this::mapToCartItemDTO).collect(Collectors.toList()));
        return cartDTO;
    }

    private CartItemDtoResponse mapToCartItemDTO(CartItem cartItem) {
        CartItemDtoResponse cartItemDtoResponse = new CartItemDtoResponse();
        cartItemDtoResponse.setCartItemId(cartItem.getCartItemId());

        // Assuming cartItem.getProduct() returns a Product object
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(cartItem.getProduct().getProductId());
        productDTO.setProductName(cartItem.getProduct().getProductName());
        productDTO.setProductPrice(cartItem.getProduct().getProductPrice());
        productDTO.setProductDescription(cartItem.getProduct().getProductDescription());
        productDTO.setCategoryId(cartItem.getProduct().getCategory().getId());
        productDTO.setCategoryName(cartItem.getProduct().getCategory().getCategoryName());


        cartItemDtoResponse.setProduct(productDTO);
        cartItemDtoResponse.setQuantity(cartItem.getQuantity());

        return cartItemDtoResponse;
    }
}