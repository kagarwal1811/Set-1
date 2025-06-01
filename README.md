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
