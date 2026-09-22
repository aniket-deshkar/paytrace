package com.paytrace.provider;
import java.math.BigDecimal; import java.util.UUID;
public interface PaymentProviderPort { ProviderResult createAndCapture(UUID paymentId, BigDecimal amount, String currency, ProviderFaultMode faultMode); ProviderResult lookup(String providerPaymentId); }
