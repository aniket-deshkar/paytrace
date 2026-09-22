# Idempotency

The request hash contains merchant reference, canonical decimal amount, and currency. The unique idempotency key persists the logical payment. A matching request receives a replay response; a different request receives a conflict. A local keyed lock is paired with the persistence constraint for local single-node behavior.
