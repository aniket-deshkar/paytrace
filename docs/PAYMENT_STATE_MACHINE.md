# Payment State Machine

Valid transitions include `CREATED → INITIATED → AUTHORIZED → CAPTURED → SETTLED`. Captured or settled payments can be partially or fully refunded. A terminal failed, cancelled, reversed, or refunded payment cannot transition to capture. `UNKNOWN` is non-transitionable.
