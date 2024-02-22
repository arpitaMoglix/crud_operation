package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CartItemDtoRequest;
import com.example.springboot.project.dto.CartItemDtoResponse;
import com.example.springboot.project.dto.ProductDTO;
import com.example.springboot.project.entities.Cart;
import com.example.springboot.project.entities.CartItem;
import com.example.springboot.project.entities.Product;
import com.example.springboot.project.repository.CartItemRepository;
import com.example.springboot.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService implements CartItemServiceInterface {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private productService productService;

    @Autowired
    private CartRepository cartRepository;

    public CartItemDtoResponse addProductToCart(Long cartId, CartItemDtoRequest cartItemDTO) {
        // Validate input DTO
        if (cartItemDTO.getProduct() == null || cartItemDTO.getQuantity() <= 0) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid CartItemDTO");
        }

        // Fetch product details by productId from ProductService
        ProductDTO productDto = productService.getProductById(cartItemDTO.getProduct().getProductId());

        // Fetch Cart entity by cartId
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        // Map ProductDTO to Product entity
        Product product = mapToProductEntity(productDto);

        // Create CartItem entity
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setCart(cart); // Associate the CartItem with the Cart

        // Save CartItem entity
        cartItem = cartItemRepository.save(cartItem);

        // Map to DTO and return
        return mapToDTO(cartItem);
    }

    public void removeProductFromCart(Long cartItemId) {
        // Remove CartItem by id
        cartItemRepository.deleteById(cartItemId);
    }

    private Product mapToProductEntity(ProductDTO productDto) {
        // Map ProductDTO to Product entity
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductDescription(productDto.getProductDescription());
        return product;
    }

    private CartItemDtoResponse mapToDTO(CartItem cartItem) {
        // Map CartItem entity to CartItemDtoResponse
        CartItemDtoResponse cartItemDTO = new CartItemDtoResponse();
        cartItemDTO.setCartItemId(cartItem.getCartItemId());
        cartItemDTO.setProduct(mapToProductDto(cartItem.getProduct()));
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }

    private ProductDTO mapToProductDto(Product product) {
        // Map Product entity to ProductDTO
        ProductDTO productDto = new ProductDTO();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setProductDescription(product.getProductDescription());
        return productDto;
    }
}
