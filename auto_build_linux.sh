./gradlew lint
./gradlew javadoc
./gradlew assembleDebug
scp -o "StrictHostKeyChecking no" -i key_linux.ppk app/build/outputs/apk/debug/app-debug.apk c4367falsyQuiz@wh04.rackhost.hu:../../web/vadaszfoto.hu/FalsyQuiz