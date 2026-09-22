# API

All API routes use `/api/v1`. Payment creation requires the `Idempotency-Key` header. Provider faults can be selected with the synthetic `X-Provider-Fault` header. Errors use an RFC 7807-style problem response with `type`, `title`, `status`, and `detail`.
