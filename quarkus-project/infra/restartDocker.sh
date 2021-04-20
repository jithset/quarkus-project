$processes = Get-Process "*docker desktop*"
if ($processes.Count -gt 0)
{
    $processes[0].Kill()
    $processes[0].WaitForExit()
}
Start-Process "C:\Program Files\Docker\Docker\Docker Desktop.exe"

