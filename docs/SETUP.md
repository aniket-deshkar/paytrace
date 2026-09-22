# Setup

Install Java 21+ and Maven 3.9+. Copy `.env.example` to `.env`, then run the platform script in `scripts`. H2 data is stored under `backend/data` unless `PAYTRACE_DATA_DIR` supplies another filesystem path. `PAYTRACE_AI_ENABLED=false` uses deterministic behavior and makes no external AI call.
