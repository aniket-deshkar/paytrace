#!/usr/bin/env sh
set -eu
ROOT="$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)"
[ -f "$ROOT/.env" ] && set -a && . "$ROOT/.env" && set +a
(cd "$ROOT/backend" && mvn spring-boot:run) &
cd "$ROOT/frontend"
[ -d node_modules ] || npm install
npm run dev
