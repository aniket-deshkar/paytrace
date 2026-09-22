package com.paytrace.provider;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.math.BigDecimal; import java.util.UUID;
@Service public class MockPaymentProvider implements PaymentProviderPort {
 private final ProviderPaymentRepository repository; public MockPaymentProvider(ProviderPaymentRepository repository){this.repository=repository;}
 @Transactional public ProviderResult createAndCapture(UUID paymentId, BigDecimal amount, String currency, ProviderFaultMode mode){
   if(mode==ProviderFaultMode.TIMEOUT_BEFORE_PROCESSING) throw new ProviderTimeoutException("Provider did not receive request");
   ProviderPaymentEntity provider=repository.findByPaymentId(paymentId).orElseGet(()->repository.save(new ProviderPaymentEntity(paymentId,amount,currency)));
   if(mode==ProviderFaultMode.FAILED_CAPTURE){return new ProviderResult(provider.providerPaymentId(),ProviderState.FAILED,amount,currency,provider.captureCount());}
   if(provider.state()!=ProviderState.CAPTURED) { provider.capture(); repository.save(provider); }
   if(mode==ProviderFaultMode.TIMEOUT_AFTER_CAPTURE) throw new ProviderTimeoutException("Provider captured but response was unavailable");
   return new ProviderResult(provider.providerPaymentId(),provider.state(),provider.amount(),provider.currency(),provider.captureCount());
 }
 public ProviderResult lookup(String providerPaymentId){var p=repository.findByProviderPaymentId(providerPaymentId).orElseThrow(()->new IllegalArgumentException("Unknown provider payment"));return new ProviderResult(p.providerPaymentId(),p.state(),p.amount(),p.currency(),p.captureCount());}
 public static class ProviderTimeoutException extends RuntimeException { public ProviderTimeoutException(String message){super(message);} }
}
