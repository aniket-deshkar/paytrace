package com.paytrace.timeline;
import com.paytrace.payment.*; import com.paytrace.state.PaymentStateMachine; import org.springframework.stereotype.Component; import java.util.*;
@Component public class CanonicalStateResolver {
 private final PaymentStateMachine machine; public CanonicalStateResolver(PaymentStateMachine machine){this.machine=machine;}
 public Resolution resolve(List<PaymentEventEntity> events){
   var sorted=events.stream().sorted(Comparator.comparing(PaymentEventEntity::occurredAt).thenComparing(PaymentEventEntity::receivedAt)).toList();
   PaymentState state=PaymentState.CREATED; var anomalies=new ArrayList<String>();
   for(var e:sorted){var target=stateFor(e.eventType()); if(target==null)continue; if(machine.isValid(state,target) || (state==PaymentState.CREATED && target==PaymentState.AUTHORIZED))state=target; else anomalies.add("Invalid chronological transition " + state + " -> " + target + " from " + e.eventType());}
   for(int i=1;i<events.size();i++)if(events.get(i).occurredAt().isBefore(events.get(i-1).occurredAt())){anomalies.add("OUT_OF_ORDER_DELIVERY");break;}
   return new Resolution(state,List.copyOf(anomalies),sorted);
 }
 private PaymentState stateFor(PaymentEventType type){return switch(type){case PAYMENT_CREATED->PaymentState.CREATED;case PAYMENT_INITIATED,PROVIDER_REQUEST_SENT->PaymentState.INITIATED;case PAYMENT_AUTHORIZED,PROVIDER_AUTHORIZED->PaymentState.AUTHORIZED;case PAYMENT_CAPTURED,PROVIDER_CAPTURED->PaymentState.CAPTURED;case PAYMENT_FAILED,PROVIDER_FAILED->PaymentState.FAILED;case PAYMENT_CANCELLED->PaymentState.CANCELLED;case PAYMENT_REVERSED->PaymentState.REVERSED;case PAYMENT_REFUNDED,PROVIDER_REFUNDED->PaymentState.REFUNDED;case PAYMENT_SETTLED,SETTLEMENT_RECEIVED->PaymentState.SETTLED;default->null;};}
 public record Resolution(PaymentState canonicalState,List<String> anomalies,List<PaymentEventEntity> canonicalTimeline){}
}
