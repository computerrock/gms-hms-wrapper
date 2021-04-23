# Support library for Google and Huawei mobile services

Content

## Download
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/computerrock/gms-hms-wrapper.git
```

## SETUP
Add both gms and hms repositories and dependencies to top-level build.gradle file
```groovy
buildscript {
    repositories {
        ...
        google()
        maven { url 'https://developer.huawei.com/repo/' } // HUAWEI Maven repository
    }
    dependencies {
        ...
        classpath 'com.google.gms:google-services:4.3.3' // google-services plugin
        classpath 'com.huawei.agconnect:agcp:1.4.1.300'  // HUAWEI agcp plugin
    }
}

allprojects {
    repositories {
        ...
        google()
        maven { url 'https://developer.huawei.com/repo/' } // HUAWEI Maven repository
    }
}
```
Include all the submodules that you will use in the `settings.gradle`
```groovy
include ':pushServices', ':locationServices', ':vision', ':tasks', ':basement'

project(':pushServices').projectDir = new File(rootDir, 'hms-lib/pushServices')
project(':locationServices').projectDir = new File(rootDir, 'hms-lib/locationServices')
project(':vision').projectDir = new File(rootDir, 'hms-lib/vision')
project(':tasks').projectDir = new File(rootDir, 'hms-lib/tasks')
project(':basement').projectDir = new File(rootDir, 'hms-lib/basement')
```
Apply hms-lib plugins in your `app` module
```groovy
apply from: '../hms-lib/scripts/productFlavor.gradle'
apply from: '../hms-lib/scripts/plugin.gradle'
```

Add `services` flavorDimension  
In this example `default` is your own applications flavorDimension
```groovy
flavorDimensions "default", "services"
```
Assign your dimension to all of your productFlavors (in this example `default`)
```groovy
productFlavors {
     dev {
        dimension "default"
        ...
     }

     prod {
        dimension "default"
        ...
     }
}
```
Implement modules as needed
```groovy
...
implementation project(':pushServices')
implementation project(':locationServices')
...
```
Add `google-services.json` and `agconnect-service.json` as you would normally do

## Modules
Following modules are implemented so far  
- `locationServices`:
  - gms: `com.google.android.gms:play-services-location`
  - hms: `com.huawei.hms:location`
- `pushServices`:
  - gms: `com.google.firebase:firebase-messaging`
  - hms: `com.huawei.hms:push`
- `analyticsServices`:
  - gms: `com.google.firebase:firebase-analytics`
  - hms: `com.huawei.hms:hianalytics`
- `vision`:
  - gms: `com.google.android.gms:play-services-vision`
  - hms: `com.huawei.hms:scan`, `com.huawei.hms:ml-computer-vision-cloud`


### locationServices
Replace all gms imports with hms-lib imports

### vision
Replace all gms imports with hms-lib imports

### pushServices
- Remove all declarations of `FirebaseInstanceIdService` from `AndroidManifest`
- Add your google-services.json and agconnect-services.json files in order to make push notifications working (it is required!)
- Register an Observer for the new token and push messages. Most likely this is done in the `Application` class
  ```kotlin
  PushServiceManagerProxy.addObserver(object: PushServiceObserver {
        override fun onNewToken(token: String) {
            //handle new token
        }
    
        override fun onMessageReceived(message: RemoteMassage) {
            //handle remote message
        }
  })
  ```
- If you have some specific implementation to gms or hms services you can add it in hms or gms folder in main project which is followed by flavors from the library
- For signing options add these lines into app build.gradle:
```gradle
signingConfigs {
        demo {
            keyAlias '<your alias>'
            keyPassword '<your password>'
            storeFile file('../<your keystore file>.jks')
            storePassword '<your store password>'
        }
    }
```