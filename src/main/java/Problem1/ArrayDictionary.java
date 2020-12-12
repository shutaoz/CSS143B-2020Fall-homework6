package Problem1;

public class ArrayDictionary implements Dictionary {
    private KVEntry[] entries;

    public ArrayDictionary(int capacity) {
        entries = new KVEntry[capacity];
    }

    @Override
    public boolean isEmpty() {
        // NOT IMPLEMENTED YET
        return false;
    }

    private int hashFunction(String key) {
        // not ideal
        return key.length();
    }

    @Override
    public void put(String key, String value) {

        int hashedKey = hashFunction(key);

        // when there's no entry yet
        if (entries[hashedKey] == null) {
            entries[hashedKey] = new KVEntry(key, value);
            return;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr.next != null) {
            // update value if key already exists
            if (ptr.key.equals(key)) {
                ptr.value = value;
                return;
            }
            ptr = ptr.next;
        }

        // add an entry to the end of the chain
        ptr.next = new KVEntry(key, value);
    }

    @Override
    public void remove(String key) {
        // homework
        int hashKey = hashFunction(key);

        if(!contains(key))
            return;

            //case 0 when there is no list chained to the existing one
        else if(entries[hashKey].next == null)
            entries[hashKey] = null;

            //case 1 when there is chaining in the list and target is the first in the list
        else if(getLoc(key).next != null)
            entries[hashKey] = getLoc(key).next;

            //case 2 when there is chaining but target is not the first
        else
            getPrev(key).next = getPrev(key).next.next;








    }

    @Override
    public String get(String key) {
        int hashedKey = hashFunction(key);

        if (entries[hashedKey] == null) {
            return null;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr != null) {
            if (ptr.key.equals(key)) {
                return ptr.value;
            }
            ptr = ptr.next;
        }
        return null;
    }

    public KVEntry getLoc(String key) {
        int hashedKey = hashFunction(key);

        KVEntry ptr = entries[hashedKey];
        while (ptr != null) {
            if (ptr.key.equals(key)) {
                return ptr;
            }
            ptr = ptr.next;
        }
        return null;
    }

    public KVEntry getPrev(String key) {
        int hashedKey = hashFunction(key);

        KVEntry ptr = entries[hashedKey];
        while (ptr.next != null) {
            if (ptr.next.key.equals(key)) {
                return ptr;
            }
            ptr = ptr.next;
        }
        return null;
    }



    @Override
    public boolean contains(String key) {
        int hashedKey = hashFunction(key);

        if (hashedKey >= entries.length) {
            return false;
        }

        if (entries[hashedKey] == null) {
            return false;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr != null) {
            if (ptr.key.equals(key)) {
                return true;
            }
            ptr = ptr.next;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) {
                builder.append("dictionary[" + i + "] = null\n");
                continue;
            }
            KVEntry ptr = entries[i];
            builder.append("dictionary[" + i + "] = {");
            while (ptr != null) {
                builder.append("(" + ptr.key + ", " + ptr.value + ")");
                ptr = ptr.next;
            }
            builder.append("}\n");
        }
        return builder.toString();
    }
}
