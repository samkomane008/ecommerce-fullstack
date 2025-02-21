package com.order_service.order_service.client;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@FeignClient(value = "inventory", url = "http://localhost:8082") // to be removed
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @RequestMapping(method = RequestMethod.GET, value = "api/inventory") // to be changed to -> @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for sku code {}, failure reason: {}", skuCode, throwable.getMessage());
        return false;
    }
}
