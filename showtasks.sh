#!/usr/bin/env bash


fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}
setFirefox() {
    xdg-settings set default-web-browser firefox.desktop
}
runTasksInBrowser() {

  xdg-open http://localhost:8080/crud/v1/task/getTasks
}

if ./runcrud.sh; then
   setFirefox
   runTasksInBrowser

else

   fail
fi