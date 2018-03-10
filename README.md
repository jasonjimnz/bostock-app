# Bostock APP

Bostock Android Application for helping people to get relevance between people with allergies
with restaurant dishes (and similar places)

# GRADLE Dependencies
- build.gradle (app)
```gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.google.android.gms:play-services-maps:11.8.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    compile "org.jetbrains.anko:anko:$anko_version"
    compile 'com.github.kittinunf.result:result:1.3.0'
    compile 'com.github.kittinunf.fuel:fuel:1.12.1' //for JVM
    compile 'com.github.kittinunf.fuel:fuel-android:1.12.1' //for Android
    compile 'com.android.support:multidex:1.0.3'
    compile 'com.google.android.gms:play-services:11.8.0'
    compile 'com.ninenine.reactivelocation:reactive-location:0.0.2'
}
```
-build.gradle Project
```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'http://dl.bintray.com/99/android' }
    }
}
```

# Installation
- The app is prepared to run in a computer with Android Studio and Android emulator
- For using without local environment you only have to find/replace "http://10.0.2.2:3000" for "http://<YOUR-DEV-SERVER-IP>:3000"
- Tested on Android 6.0 and 7.1


# Screenshots
![Bostock IMG1](screenshoots/Screenshot_1520685076.png)
![Bostock IMG2](screenshoots/Screenshot_1520685091.png)
![Bostock IMG3](screenshoots/Screenshot_1520685195.png)
![Bostock IMG4](screenshoots/Screenshot_1520685203.png)
![Bostock IMG1](screenshoots/Screenshot_1520685208.png)
