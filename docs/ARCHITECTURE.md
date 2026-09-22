# Architecture

PayTrace uses a feature-oriented Spring backend and a separate Next.js operator UI. H2 file mode retains persisted evidence. The event store preserves `occurredAt` and `receivedAt` to separate financial order from arrival order.

The deterministic pipeline is normalization → state machine → canonical resolver → invariants → incidents → recovery policy. Optional decision and explanation ports receive compact evidence only; their outputs cannot override canonical state or recovery action.
