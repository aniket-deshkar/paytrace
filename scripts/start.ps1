$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $PSScriptRoot
if (Test-Path (Join-Path $root '.env')) { Get-Content (Join-Path $root '.env') | Where-Object { $_ -match '^[A-Za-z_][A-Za-z0-9_]*=' } | ForEach-Object { $pair = $_.Split('=',2); [Environment]::SetEnvironmentVariable($pair[0], $pair[1], 'Process') } }
Start-Process -FilePath 'mvn' -ArgumentList 'spring-boot:run' -WorkingDirectory (Join-Path $root 'backend') -WindowStyle Hidden
Set-Location (Join-Path $root 'frontend')
if (-not (Test-Path 'node_modules')) { npm install }
npm run dev
