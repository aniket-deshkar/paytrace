package com.paytrace.payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> { Optional<PaymentEntity> findByIdempotencyKey(String idempotencyKey); }
