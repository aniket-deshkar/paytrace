$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $PSScriptRoot
Push-Location (Join-Path $root 'backend'); mvn clean verify; Pop-Location
Push-Location (Join-Path $root 'frontend'); if (-not (Test-Path 'node_modules')) { npm install }; npm run typecheck; npm run build; Pop-Location
Get-ChildItem $root -Recurse -File | Select-String -Pattern 'current status|development status|project status|work in progress|\bWIP\b|coming soon|roadmap|what''s next|next steps|future work' -CaseSensitive:$false
