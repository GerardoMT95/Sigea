set namespace=%1
set deployment=%2
set container=%3
set immagine=%4
kubectl set image deployment/%deployment% %container%=%immagine% -n %namespace%
kubectl rollout restart deployment/%deployment% -n %namespace%