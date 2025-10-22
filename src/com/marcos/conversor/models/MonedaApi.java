package com.marcos.conversor.models;

import java.util.Map;

public record MonedaApi(Map<String, Double> conversion_rates) {
}
