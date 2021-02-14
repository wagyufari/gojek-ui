export PATH=/opt/homebrew/bin:$PATH
./gradlew assembleDevelopmentDebug && ./gradlew installDevelopmentDebug
adb shell am force-stop com.mayburger.gojekuiclone
adb shell am start -n com.mayburger.gojekuiclone/.ui.main.MainActivity