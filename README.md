# LRUCache & MyHashMap in C++

This repository contains two C++ implementations of common data structures:

1. **LRUCache.cpp** – Implements a Least Recently Used (LRU) Cache.  
2. **MyHashMap.cpp** – Implements a basic Hash Map without using `std::map` or `std::unordered_map`.

---

## LRUCache

### Description
`LRUCache` is a fixed-size cache that removes the least recently used key when the capacity is exceeded. It supports:

- `get(key)` → Returns the value if the key exists, otherwise returns -1.
- `put(key, value)` → Inserts or updates the key-value pair. If the cache is full, it evicts the least recently used key.

### Approach
- Uses a **doubly linked list** to maintain the order of usage.
- Uses a **hash map** to store key → node mappings for O(1) access.

### Example Usage
```cpp
LRUCache cache(2);
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);    // returns 1
cache.put(3, 3); // evicts key 2
cache.get(2);    // returns -1
```

# MyHashMap 

This repository contains a C++ implementation of a simple Hash Map without using `std::map` or `std::unordered_map`.

---

## Description

`MyHashMap` is a custom-built data structure that stores integer key-value pairs. It supports:

- `put(key, value)` → Inserts or updates the key-value pair.
- `get(key)` → Returns the value if the key exists, otherwise returns -1.
- `remove(key)` → Deletes the key if it exists.

---

## Approach

- Uses a **fixed-size array of buckets** implemented using `std::vector<std::list<pair<int, int>>>`.
- Uses **separate chaining** to handle collisions.
- Hash function used: `key % BUCKET_COUNT`, where `BUCKET_COUNT` is a prime number (2069) to ensure better distribution.

---

## Example Usage

```cpp
MyHashMap map;
map.put(1, 10);
map.put(2, 20);
map.get(1);      // returns 10
map.remove(2);
map.get(2);      // returns -1
```

# Book App

Book App is a Java-based Android application that demonstrates a clean, MVVM-inspired architecture. It fetches a list of books from a local JSON “API,” displays them in a scrollable list, allows users to view detailed information (title, author, description, rating), and save favorites locally for offline access. This README explains how to set up and run the app on any development machine.

---

## Features

- **Book List**: Fetches and displays titles and authors from a mock JSON API.
- **Book Detail**: Shows full description and rating.
- **Favorites**: “Add to Favorites” saves to a local Room database so favorites persist when offline.
- **Offline Mode**: In airplane mode, only favorited books are displayed.
- **MVVM-Inspired Architecture**: Activities → ViewModel → Repository → Data sources, using LiveData for UI updates.

---

## Tech Stack

- Java 8  
- AndroidX (AppCompat, RecyclerView, Lifecycle, Room)  
- Retrofit with a custom interceptor for fake API responses  
- Room (SQLite) for local persistence  
- LiveData & ViewModel for reactive UI

---

## Prerequisites

Before running Book App, ensure your development environment includes:

1. **Android Studio** (version Arctic Fox / Electric Eel / Flamingo or newer).  
2. **Android SDK** with:  
   - API Level 34 (Android 14)  
   - Build-Tools 34.0.0  
3. **Java JDK 1.8** (bundled with recent Android Studio).  
4. **An Emulator** (e.g., Pixel 5 with API 34) or **a Physical Android Device** with USB debugging enabled.  

---

## How to Run the Application

1. **Clone or Download the Repository**  
   - Via Git:
     ```bash
     git clone https://github.com/<your-username>/BookApp.git
     cd BookApp
     ```
   - Or download the ZIP from GitHub, then extract it to a local folder (for example, `C:\Projects\BookApp` on Windows or `~/Projects/BookApp` on macOS/Linux).

2. **Open in Android Studio**  
   - Launch **Android Studio**.  
   - Click **File → Open…** (or “Open an existing project”).  
   - Navigate to the folder containing `settings.gradle` and the `app/` directory.  
   - Select that folder and click **OK**.  
   - Android Studio will automatically begin a Gradle sync.

3. **Sync & Build**  
   - Wait for the blue “Gradle sync finished” message in the status bar.  
   - If prompted to install missing SDK components or update Gradle plugins, click **Install** / **Update**.  
   - Click the **Sync Project with Gradle Files** icon (elephant ↻) to force a manual sync if needed.  
   - After sync, click **Build → Make Project** (or press **Ctrl+F9**).  
   - Verify that you see “BUILD SUCCESSFUL” in the Build window.

4. **Set Up an Emulator or Connect a Physical Device**  
   - **To use an emulator**:  
     1. Open **Device Manager** (phone icon) in the top toolbar.  
     2. Click **Create device → Select Pixel 5 (or any device) → Next**.  
     3. Under “Recommended system images,” choose **Android 14 (API 34)** → click **Download** → once downloaded, click **Finish**.  
     4. Back in Device Manager, click the ▶ button next to your new emulator to launch it.  
   - **To use a physical device**:  
     1. On your Android device, enable **Developer Options** (tap “Build number” seven times under Settings → About phone).  
     2. In Settings → System → Developer Options, turn on **USB debugging**.  
     3. Connect the device to your computer via USB. Accept any “Allow USB debugging?” prompts on the device.

5. **Run the App**  
   - In Android Studio’s toolbar, select the **BookApp** run configuration.  
   - In the device dropdown (next to the Run button), choose your running emulator (e.g., “Pixel 5 API 34”) or connected USB device.  
   - Click the green **▶ Run** button.  
   - The app will install and launch on the selected device. You should see a list of sample books.

6. **Test Offline Favorites**  
   - In the emulator or on your device, enable **Airplane Mode**.  
   - Close and reopen Book App; only the books you previously marked as favorites will appear.

---

Thank you.  
