#include <iostream>
#include <unordered_map>
/*
 * LRUCache
 
 * Stores up to capacity key→value pairs. When capacity is exceeded,
 * uses the least recently used item. Both get() and put() run in O(1).
 */
class LRUCache
{
private:
    struct Node
    {
        int key, value;
        Node *prev;
        Node *next;
        Node(int k, int v) : key(k), value(v), prev(nullptr), next(nullptr) {}
    };

    int capacity;
    Node *head; // dummy head
    Node *tail; // dummy tail
    std::unordered_map<int, Node *> map;

    // Detach node from its current position
    void remove(Node *node)
    {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }

    // Insert node right after dummy head (mark as most recently used)
    void insertAtFront(Node *node)
    {
        node->next = head->next;
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }

public:
    LRUCache(int cap) : capacity(cap)
    {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head->next = tail;
        tail->prev = head;
    }
    ~LRUCache()
    {
        Node *curr = head;
        while (curr)
        {
            Node *nxt = curr->next;
            delete curr;
            curr = nxt;
        }
    }
    int get(int key)
    {
        auto it = map.find(key);
        if (it == map.end())
            return -1;

        Node *node = it->second;
        remove(node);
        insertAtFront(node);
        return node->value;
    }
    void put(int key, int value)
    {
        auto it = map.find(key);
        if (it != map.end())
        {
            Node *existing = it->second;
            existing->value = value;
            remove(existing);
            insertAtFront(existing);
            return;
        }
        if ((int)map.size() == capacity)
        {
            Node *lru = tail->prev;
            map.erase(lru->key);
            remove(lru);
            delete lru;
        }
        Node *node = new Node(key, value);
        insertAtFront(node);
        map[key] = node;
    }
};
int main()
{
    LRUCache cache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    std::cout << cache.get(1) << "  (expected 1)\n";
    cache.put(3, 3); // evicts key 2
    std::cout << cache.get(2) << "  (expected -1)\n";
    cache.put(4, 4); // evicts key 1
    std::cout << cache.get(1) << "  (expected -1)\n";
    std::cout << cache.get(3) << "  (expected 3)\n";
    std::cout << cache.get(4) << "  (expected 4)\n";
    return 0;
}