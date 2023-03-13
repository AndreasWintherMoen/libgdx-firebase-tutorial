# LibGDX + Firebase Tutorial TDT4240

## March 2023 - TDT4240

1. Create a new [Firebase project](https://console.firebase.google.com/u/0/) (you don't need Google Analytics)
2. Register a new Android app. Package name should match your project.
3. Download the generated _google-services.json_ file and put it in your _android/src_ folder.
4. Add the following to your **root level** _build.gradle_ file (see the _build.gradle_ file in [this commit](https://github.com/AndreasWintherMoen/libgdx-firebase-tutorial/commit/8427ca09a0468582037acfdea24d18bf76866d00#diff-49a96e7eea8a94af862798a45174e6ac43eb4f8b4bd40759b5da63ba31ec3ef7) for all changes made. You can also check out [this](https://firebase.google.com/docs/database/android/start#java) Firebase tutorial):
   - In _buildscripts dependencies_: `classpath 'com.google.gms:google-services:4.3.15'`
   - In _android project_: `apply plugin: 'com.google.gms.google-services'`
   - In _android project dependencies_: `implementation platform('com.google.firebase:firebase-bom:31.2.3')` and `implementation 'com.google.firebase:firebase-database'`
5. Add _android.useAndroidX=true_ to your _gradle.properties_ file.
6. Add a realtime database to your Firebase project. Either select default location (us-central) or one closer (e.g. europe-west). Note: if you don't use the default, you will have to provide the database URL when running `FirebaseDatabase.getInstance();`.
7. Set read and write rules to _true_. Authenticating database access is beyond the scope of this project.
8. Create an _Api_ interface in the _core_ module and a platform specific implementation in each other module. You can follow [this](https://libgdx.com/wiki/app/interfacing-with-platform-specific-code) LibGDX guide.
9. In the Android implementation, read from and write to the database as shown in the [Firebase docs](https://firebase.google.com/docs/database/android/start#java). For the other platforms (particularly the desktop application) you won't be able to communicate with Firebase. There is a [cross-platform API](https://github.com/mk-5/gdx-fireapp) for this purpose, but it doesn't seem to work with desktop launcher either. If you find a good way of doing this, please let us know :)
10. Good luck with your semester project!
