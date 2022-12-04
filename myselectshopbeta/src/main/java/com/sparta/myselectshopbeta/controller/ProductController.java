package com.sparta.myselectshopbeta.controller;

import com.sparta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.sparta.myselectshopbeta.dto.ProductRequestDto;
import com.sparta.myselectshopbeta.dto.ProductResponseDto;
import com.sparta.myselectshopbeta.entity.Product;
import com.sparta.myselectshopbeta.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 관심 상품 등록하기
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, HttpServletRequest request) {
        // 응답 보내기
        return productService.createProduct(requestDto, request);
    }

    // 관심 상품 조회하기
    @GetMapping("/products")
    public Page<Product> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            HttpServletRequest request
    ) {
        // 응답 보내기
        return productService.getProducts(request, page-1, size, sortBy, isAsc);
    }

    // 관심 상품 최저가 등록하기
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto, HttpServletRequest request) {
        // 응답 보내기 (업데이트된 상품 id)
        return productService.updateProduct(id, requestDto, request);
    }

    // 상품에 폴더 추가
    @PostMapping("/products/{productId}/folder")
    public Long addFolder(
            @PathVariable Long productId,
            @RequestParam Long folderId,
            HttpServletRequest request
    ) {
        Product product = productService.addFolder(productId, folderId, request);
        return product.getId();
    }

}