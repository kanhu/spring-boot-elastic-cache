package com.krushna.springbootelasticcache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.krushna.springbootelasticcache.model.Product;

@Service
public class ProductService {
	Logger log = LoggerFactory.getLogger(ProductService.class);

	@CacheEvict(key = "{#product.id}", value = "getProduct")
	public void saveProduct(Product product) {
		// call to DB to save the product
	}

	/**
	 * It is used on the method level to let spring know that the response of the
	 * method are cacheable. Spring manages the request/response of this method to
	 * the cache specified in annotation attribute. For example, @Cacheable
	 * ("cache-name1", “cache-name2”).
	 * 
	 * @param productId
	 * @return
	 */
	@Cacheable(key = "{#productId}", value = "getProduct")
	public Product getProduct(String productId) {
		// Call to DB or heavy operation
		log.info("Entering method getProduct entry not found from cache");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
		return new Product("TesProduct", "123", "TestDescription");
	}

	/**
	 * We can specify key here to remove cache, if we need to remove all the entries
	 * of the cache then we need to use allEntries=true. This option comes in handy
	 * when an entire cache region needs to be cleared out – rather then evicting
	 * each entry (which would take a long time since it is inefficient), all the
	 * entries are removed in one operation.
	 * 
	 * @param id
	 */
	@CacheEvict(key = "{#productId}", value = "getProduct")
	public void deleteProduct(String id) {
		// call the DB or API to delete the product
	}
}
