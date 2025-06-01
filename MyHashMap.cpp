#include <iostream>
#include <vector>
#include <list>
#include <utility>
/*
 * MyHashMap
 * A simple hash‐map of int→int without using unordered_map.
 * Uses BUCKET_COUNT buckets with separate chaining for collisions.
 */
class MyHashMap
{
private:
    static const int BUCKET_COUNT = 2069;
    std::vector<std::list<std::pair<int, int>>> buckets;

    int hash(int key) const
    {
        return key % BUCKET_COUNT;
    }

public:
    MyHashMap() : buckets(BUCKET_COUNT) {}
    void put(int key, int value)
    {
        int b = hash(key);
        for (auto &p : buckets[b])
        {
            if (p.first == key)
            {
                p.second = value;
                return;
            }
        }
        buckets[b].emplace_back(key, value);
    }

    int get(int key)
    {
        int b = hash(key);
        for (auto &p : buckets[b])
        {
            if (p.first == key)
            {
                return p.second;
            }
        }
        return -1;
    }
    void remove(int key)
    {
        int b = hash(key);
        auto &lst = buckets[b];
        for (auto it = lst.begin(); it != lst.end(); ++it)
        {
            if (it->first == key)
            {
                lst.erase(it);
                return;
            }
        }
    }
};
int main()
{
    MyHashMap map;
    map.put(1, 10);
    map.put(2, 20);
    std::cout << map.get(1) << "  (expected 10)\n";
    std::cout << map.get(3) << "  (expected -1)\n";
    map.put(2, 30);
    std::cout << map.get(2) << "  (expected 30)\n";
    map.remove(2);
    std::cout << map.get(2) << "  (expected -1)\n";
    return 0;
}
