# Spotlight
Spotlight is an Android library used to onboard users by showcasing specific features in the app.this library build from https://github.com/29jitender/Spotlight library but make some update to support for latest version of android.

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
<img src="https://img.shields.io/badge/license-Apache 2.0-green.svg?style=flat">
[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![](https://jitpack.io/v/polekar-ankit/Spotlight.svg)](https://jitpack.io/#polekar-ankit/Spotlight)
# Screen
<img src="https://raw.githubusercontent.com/wooplr/Spotlight/master/art/intro.gif?token=AA5ZAHdvAspW6Zj8YyyKamkV7jWXFtMHks5XaQovwA%3D%3D"/>

# Usage
```java
new SpotlightView.Builder(this)
        .introAnimationDuration(400)
        .enableRevealAnimation(isRevealEnabled)
        .performClick(true)
        .fadeinTextDuration(400)
        .headingTvColor(Color.parseColor("#eb273f"))
        .headingTvSize(32)
        .headingTvText("Love")
        .subHeadingTvColor(Color.parseColor("#ffffff"))
        .subHeadingTvSize(16)
        .subHeadingTvText("Like the picture?\nLet others know.")
        .maskColor(Color.parseColor("#dc000000"))
        .target(view)
        .lineAnimDuration(400)
        .lineAndArcColor(Color.parseColor("#eb273f"))
        .dismissOnTouch(true)
        .dismissOnBackPress(true)
        .enableDismissAfterShown(true)
        .usageId(usageId) //UNIQUE ID
        .show();
```

## Download
### Gradle

1. Define the jitpack remote Maven repository inside the repositories block of your root `build.gradle` file

    ```javascript
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
    ```

2. Add the Spotlight dependency

    ```javascript
    dependencies {
        ...
         implementation 'com.github.polekar-ankit.Spotlight:final:0.3.0'
    }
    ```



# Builder Methods

### maskColor(int)
Overlay Color

### target(View)
View to showcase

### introAnimationDuration(long)
Intro animation duration (For Reveal and Fadein)

### enableRevealAnimation(boolean)
Enable reveal animation (Only for Lollipop and above)

### fadeinTextDuration(long)
Fade in animation duration for spotlight text (Heading and Sub-heading)

### headingTvSize(int)
Size of heading text

### headingTvColor(int)
Color of heading text

### headingTvText(CharSequence)
Text to display in heading

### subHeadingTvSize(int)
Size of sub-heading text

### subHeadingTvColor(int)
Color of sub-heading text

### subHeadingTvText(CharSequence)
Text to display in sub-heading

### setTypeface(Typeface)
Custom font for text in spotlight view

### lineAndArcColor(int)
Color of the spotlight line

### lineAnimDuration(long)
Line animation duration

### performClick(boolean)
Perform a click on target view

### usageId(String)
Unique id for each spotlight

### dismissOnTouch(boolean)
Dismiss spotlight on touch outside

### enableDismissAfterShown(boolean)
Dismiss spotlight on touch outside after spotlight is completely visible

###showSkipButton(boolean)
hide and display skip button

###setSkipButtonTextSize(int)
set text size of skip button

# Configuration Method
```java
//Create global config instance to reuse it
SpotlightConfig config = new SpotlightConfig();
config.isDismissOnTouch(true);
config.setLineAndArcColor(0xFFFFFFFF);
...
.setConfiguration(config)
```

# Author

[Ankit Polekar](https://github.com/polekar-ankit)


# Credits


## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)
