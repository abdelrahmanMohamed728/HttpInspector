
![background image](background.png)

**Http Inspector** is a powerful Android library that simplifies the debugging and monitoring of
HTTP requests in your Android applications. This tool allows you to capture, log, and visualize your
app's network activity, offering comprehensive insights into API calls, request details, response
data, and more.

## Key Features

- **Real-time Tracking:** Monitor HTTP interactions in real time.
- **Detailed Logging:** Log request and response details, including headers and payloads.
- **Integration:** Easily integrate HttpInspector into your Android projects.
- **Troubleshooting:** Diagnose issues, optimize performance, and enhance network functionality.
- **Open Source:** Adapt and extend HttpInspector to meet your specific requirements.

https://github.com/abdelrahmanMohamed728/HttpInspector/assets/31102764/6b2c776c-1c40-44bb-bb82-37aadb6d74d2

## Getting Started

1. **Installation:**

   To integrate HttpInspector into your Android project, add the following dependency to your
   module-level `build.gradle` file:

   ```gradle s

   dependencies {
       implementation 'com.github.abdelrahmanMohamed728:HttpInspector:version'
   } 

   
  Ensure that you have the JitPack repository added in your root build.gradle file:

   ```gradle s
    allprojects {
    repositories {
    // ... (your other repositories here)

        mavenCentral()
        maven { url 'https://jitpack.io' }
    
        }
    }
 ```

2. **Usage:**
   To use it you need to add the interceptor to your okHttpClient
   ```kotlin
   .addInterceptor(HttpInterceptor())
   ```

   and add the floating button to your Compose UI method so that you can see the requests you called
   ```kotlin
   HttpInspectorFloatingButton()
   ```
## License

    Copyright 2023 Coil Contributors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




