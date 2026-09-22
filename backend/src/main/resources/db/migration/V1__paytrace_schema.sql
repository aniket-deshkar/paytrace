create table payments (
  id uuid primary key, merchant_reference varchar(120) not null, idempotency_key varchar(160) not null unique,
  request_hash varchar(64) not null, amount numeric(19,2) not null, currency varchar(3) not null,
  observed_state varchar(40) not null, canonical_state varchar(40) not null, provider_payment_id varchar(120),
  created_at timestamp with time zone not null, version bigint not null
);
create table payment_events (
  id uuid primary key, payment_id uuid not null, event_type varchar(60) not null, source varchar(30) not null,
  occurred_at timestamp with time zone not null, received_at timestamp with time zone not null,
  external_reference varchar(160), payload_hash varchar(64), attributes varchar(4000) not null
);
create index ix_payment_events_payment_occurred on payment_events(payment_id, occurred_at);
create table provider_payments (
  id uuid primary key, provider_payment_id varchar(120) not null unique, payment_id uuid not null unique,
  amount numeric(19,2) not null, currency varchar(3) not null, state varchar(30) not null, capture_count integer not null,
  created_at timestamp with time zone not null, updated_at timestamp with time zone not null
);
create table webhook_inbox_events (
  id uuid primary key, provider_event_id varchar(160) not null, payment_id uuid not null, payload_hash varchar(64) not null,
  received_at timestamp with time zone not null, occurred_at timestamp with time zone not null, processing_status varchar(30) not null,
  duplicate_event boolean not null, processed_at timestamp with time zone
);
create index ix_webhook_provider_event on webhook_inbox_events(provider_event_id);
create table ledger_transactions (
 id uuid primary key, payment_id uuid not null, reference varchar(160) not null unique, created_at timestamp with time zone not null
);
create table ledger_entries (
 id uuid primary key, transaction_id uuid not null, account_code varchar(80) not null, direction varchar(10) not null, amount numeric(19,2) not null, currency varchar(3) not null
);
create table incidents (
 id uuid primary key, payment_id uuid not null, incident_type varchar(50) not null, severity varchar(20) not null,
 detected_at timestamp with time zone not null, evidence varchar(6000) not null, affected_components varchar(1000) not null, deterministic_facts varchar(6000) not null
);
create table invariant_results (
 id uuid primary key, payment_id uuid not null, invariant_name varchar(100) not null, passed boolean not null, evidence varchar(4000) not null, evaluated_at timestamp with time zone not null
);
create table recovery_recommendations (
 id uuid primary key, payment_id uuid not null, action varchar(40) not null, rationale varchar(4000) not null, created_at timestamp with time zone not null
);
