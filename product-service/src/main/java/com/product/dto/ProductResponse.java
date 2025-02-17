package com.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductResponse(String id, String name, String description, BigDecimal price) implements Serializable {
}
