# PSG


# 🚨 Emergency Branch (Android - Kotlin)

This is an Android app built in Kotlin that listens for a specific **trigger word** sent via SMS. Upon detecting the word, it performs emergency actions like:

- Sending live location
- Playing a loud alarm sound (even if the phone is in silent mode)

---

## 📲 Features

- Customizable **trigger word**
- Works in background (foreground service)
- Uses **default system ringtone** as alarm
- Retrieves **live GPS location**
- Compatible from **Android 9 (Pie) to Android 15**

---

## 🛠 Tech Stack

- Kotlin
- Android Jetpack
- Fused Location Provider API
- SharedPreferences for settings
- Foreground Service for background tasks


