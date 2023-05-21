set namespace=%1
set deployment=%2
set container=%3
set image=%4
wsl kubectl set image deployment/%deployment% %container%=%image% -n %namespace%
wsl kubectl rollout restart deployment/%deployment% -n %namespace%