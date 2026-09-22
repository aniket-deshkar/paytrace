# Webhook Processing

The provider webhook inbox stores provider event ID, payment ID, payload hash, occurred time, received time, duplicate marker, processing state, and processing time. Stable provider event IDs drive duplicate classification. Re-delivery is retained as evidence and does not reapply a financial event.
