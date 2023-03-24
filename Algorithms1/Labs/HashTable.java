public class HashTable {
  private long a;
  private long c;
  private int count = 0;

  public int buckets[];

  public HashTable(long _a, long _c, long _m) {
    a = _a;
    c = _c;
    buckets = new int[(int) _m];
  }

  public void insert(int key) {
    if (loadFactor() >= 1) {
      extend();
    }
    synchronized(this){
      int h = getInitIndex(key);
      if (buckets[h] <= 0) {
        buckets[h] = key;
      } else {
        insert(h+1, key);
      }
      count++;
    }
  }

  private void insert(int ind, int key) {
    if (ind >= buckets.length) {
      extend();
      insert(key);
    } else if (buckets[ind] <= 0) {
      buckets[ind] = key;
    } else {
      insert(ind + 1, key);
    }
  }
  
  private int getInitIndex(int key) {
    return (int)(a * key + c) % buckets.length;
  }

  public void extend() {
    synchronized(this){
      int oldBuckets[] = buckets;
      buckets = new int[buckets.length * 2];
      count = 0;
      for(int i = 0; i < oldBuckets.length; i++) {
        if (oldBuckets[i] > 0) {
          insert(oldBuckets[i]);
        }
      }
    }
  }

  public boolean find(int key) {
    return findIndex(key) < 0 ? false : true;
  }
  
  private int findIndex(int key) {
    int h = getInitIndex(key);
    if (buckets[h] <= 0) {
      return -1;
    } 
    for (int i = h; i < buckets.length; i++) {
      if(buckets[i] == key) {
        return i;
      }
    }
    return -1; 
  }
  
  private void remove(int ind, int key) {
    for (int i = buckets.length-1; i >= ind; i--) {
      if(buckets[i] == key) {
        buckets[i] = 0;
        count--;
        break;
      }
    }
  }

  public void remove(int key) {
    int ind = findIndex(key);
    if (ind >= 0) {
      synchronized(this){
        remove(ind, key);
      }
    }
  }

  public double loadFactor() {
    double factor = (1.0 * count) / buckets.length;
    return factor;
  }
}
