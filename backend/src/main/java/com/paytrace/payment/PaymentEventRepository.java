package com.paytrace.payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface PaymentEventRepository extends JpaRepository<PaymentEventEntity, UUID> { List<PaymentEventEntity> findByPaymentIdOrderByOccurredAtAsc(UUID paymentId); long countByPaymentIdAndEventType(UUID id, PaymentEventType type); }
