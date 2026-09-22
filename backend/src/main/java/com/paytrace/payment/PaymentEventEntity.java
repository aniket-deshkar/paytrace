package com.paytrace.payment;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="payment_events") public class PaymentEventEntity {
 @Id private UUID id; @Column(name="payment_id",nullable=false) private UUID paymentId; @Enumerated(EnumType.STRING) @Column(name="event_type",nullable=false) private PaymentEventType eventType; @Enumerated(EnumType.STRING) @Column(nullable=false) private EventSource source;
 @Column(name="occurred_at",nullable=false) private Instant occurredAt; @Column(name="received_at",nullable=false) private Instant receivedAt; @Column(name="external_reference") private String externalReference; @Column(name="payload_hash") private String payloadHash; @Column(nullable=false) private String attributes;
 protected PaymentEventEntity(){} public PaymentEventEntity(UUID paymentId, PaymentEventType type, EventSource source, Instant occurredAt, String externalReference, String payloadHash, String attributes){this.id=UUID.randomUUID();this.paymentId=paymentId;this.eventType=type;this.source=source;this.occurredAt=occurredAt;this.receivedAt=Instant.now();this.externalReference=externalReference;this.payloadHash=payloadHash;this.attributes=attributes;}
 public UUID id(){return id;} public UUID paymentId(){return paymentId;} public PaymentEventType eventType(){return eventType;} public EventSource source(){return source;} public Instant occurredAt(){return occurredAt;} public Instant receivedAt(){return receivedAt;} public String externalReference(){return externalReference;} public String attributes(){return attributes;}
}
