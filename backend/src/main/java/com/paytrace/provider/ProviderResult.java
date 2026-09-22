package com.paytrace.provider;
import java.math.BigDecimal;
public record ProviderResult(String providerPaymentId, ProviderState state, BigDecimal amount, String currency, int captureCount) { }
