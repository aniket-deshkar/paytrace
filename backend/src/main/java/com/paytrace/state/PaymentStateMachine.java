package com.paytrace.state;
import com.paytrace.payment.PaymentState;
import org.springframework.stereotype.Component;
import java.util.*;
@Component public class PaymentStateMachine {
 private static final Map<PaymentState, Set<PaymentState>> NEXT = Map.ofEntries(
   Map.entry(PaymentState.CREATED, Set.of(PaymentState.INITIATED, PaymentState.FAILED, PaymentState.CANCELLED)),
   Map.entry(PaymentState.INITIATED, Set.of(PaymentState.AUTHORIZED, PaymentState.FAILED, PaymentState.CANCELLED)),
   Map.entry(PaymentState.AUTHORIZED, Set.of(PaymentState.CAPTURED, PaymentState.FAILED, PaymentState.CANCELLED, PaymentState.REVERSED)),
   Map.entry(PaymentState.CAPTURED, Set.of(PaymentState.SETTLED, PaymentState.PARTIALLY_REFUNDED, PaymentState.REFUNDED, PaymentState.REVERSED)),
   Map.entry(PaymentState.SETTLED, Set.of(PaymentState.PARTIALLY_REFUNDED, PaymentState.REFUNDED)),
   Map.entry(PaymentState.PARTIALLY_REFUNDED, Set.of(PaymentState.PARTIALLY_REFUNDED, PaymentState.REFUNDED)),
   Map.entry(PaymentState.FAILED, Set.of()), Map.entry(PaymentState.CANCELLED, Set.of()), Map.entry(PaymentState.REVERSED, Set.of()), Map.entry(PaymentState.REFUNDED, Set.of()), Map.entry(PaymentState.UNKNOWN, Set.of()));
 public boolean isValid(PaymentState from, PaymentState to){ return from == to || NEXT.getOrDefault(from, Set.of()).contains(to); }
 public PaymentState transition(PaymentState from, PaymentState to){ if(!isValid(from,to)) throw new InvalidPaymentTransitionException(from,to); return to; }
 public static class InvalidPaymentTransitionException extends RuntimeException { public InvalidPaymentTransitionException(PaymentState from, PaymentState to){super("Invalid payment transition: "+from+" -> "+to);} }
}
