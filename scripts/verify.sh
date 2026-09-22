#!/usr/bin/env sh
set -eu
ROOT="$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)"
(cd "$ROOT/backend" && mvn clean verify)
(cd "$ROOT/frontend" && { [ -d node_modules ] || npm install; npm run typecheck; npm run build; })
! rg -i 'current status|development status|project status|work in progress|\bWIP\b|coming soon|roadmap|what.s next|next steps|future work' "$ROOT" --glob '!node_modules/**' --glob '!.next/**'
