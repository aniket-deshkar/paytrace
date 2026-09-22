# Security

PayTrace is a synthetic local simulator. It has no real provider, bank, or settlement integration. Secrets are read only from backend environment variables, never logged or made browser-visible. Inputs are validated and monetary values use `BigDecimal`; recovery output is advisory and never initiates a refund or reversal.
