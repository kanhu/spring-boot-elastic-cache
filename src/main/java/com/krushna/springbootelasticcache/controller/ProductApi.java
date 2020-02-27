package com.krushna.springbootelasticcache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushna.springbootelasticcache.model.Product;
import com.krushna.springbootelasticcache.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
public class ProductApi {

	@Autowired
	ProductService productService;
	Logger log = LoggerFactory.getLogger(ProductApi.class);

	@GetMapping("/product/{productId}")
	@ApiOperation(value = "Perform the get product call")
	public ResponseEntity<Product> retrieveProduct(@PathVariable String productId) {
		log.info("Entering method retrieveProduct");
		return ResponseEntity.ok(productService.getProduct(productId));
	}

	@PostMapping("/product")
	@ApiOperation(value = "Perform the save product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return ResponseEntity.ok(product);
	}

}
