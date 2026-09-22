package com.paytrace.provider;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ProviderPaymentRepository extends JpaRepository<ProviderPaymentEntity, UUID> { Optional<ProviderPaymentEntity> findByPaymentId(UUID paymentId); Optional<ProviderPaymentEntity> findByProviderPaymentId(String id); }
