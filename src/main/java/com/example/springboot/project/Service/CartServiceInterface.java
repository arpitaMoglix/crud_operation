package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CartDtoRequest;
import com.example.springboot.project.dto.CartDtoResponse;

public interface CartServiceInterface {
    CartDtoResponse getCartById(Long cartId);

    Long createCart();

  //  CartDtoResponse addProductToCart(Long cartId, CartDtoRequest cartDtoRequest);
    //CartDtoResponse addProductToCartV1(Long cartId, CartDtoRequest cartDtoRequest);
  CartDtoResponse addProductToCartV1(Long userId, CartDtoRequest cartDtoRequest);

    void removeProductFromCart(Long userId, Long CartId);

}
