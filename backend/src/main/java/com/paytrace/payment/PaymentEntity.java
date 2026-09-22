package com.paytrace.payment;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "payments")
public class PaymentEntity {
  @Id private UUID id;
  @Column(name="merchant_reference", nullable=false) private String merchantReference;
  @Column(name="idempotency_key", nullable=false, unique=true) private String idempotencyKey;
  @Column(name="request_hash", nullable=false) private String requestHash;
  @Column(nullable=false, precision=19, scale=2) private BigDecimal amount;
  @Column(nullable=false, length=3) private String currency;
  @Enumerated(EnumType.STRING) @Column(name="observed_state", nullable=false) private PaymentState observedState;
  @Enumerated(EnumType.STRING) @Column(name="canonical_state", nullable=false) private PaymentState canonicalState;
  @Column(name="provider_payment_id") private String providerPaymentId;
  @Column(name="created_at", nullable=false) private Instant createdAt;
  @Version private long version;
  protected PaymentEntity() { }
  public PaymentEntity(UUID id, String merchantReference, String idempotencyKey, String requestHash, BigDecimal amount, String currency) {
    this.id=id; this.merchantReference=merchantReference; this.idempotencyKey=idempotencyKey; this.requestHash=requestHash; this.amount=amount; this.currency=currency;
    observedState=PaymentState.CREATED; canonicalState=PaymentState.CREATED; createdAt=Instant.now();
  }
  public UUID id(){return id;} public String merchantReference(){return merchantReference;} public String idempotencyKey(){return idempotencyKey;} public String requestHash(){return requestHash;}
  public BigDecimal amount(){return amount;} public String currency(){return currency;} public PaymentState observedState(){return observedState;} public PaymentState canonicalState(){return canonicalState;}
  public String providerPaymentId(){return providerPaymentId;} public Instant createdAt(){return createdAt;}
  public void providerPaymentId(String value){providerPaymentId=value;} public void states(PaymentState observed, PaymentState canonical){observedState=observed; canonicalState=canonical;}
}
