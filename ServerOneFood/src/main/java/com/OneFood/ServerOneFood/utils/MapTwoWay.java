package com.OneFood.ServerOneFood.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTwoWay<K, V>  {

    HashMap<V,K> reverseMap ;
    HashMap<K,V> map ;

    public MapTwoWay() {
        this.reverseMap = new HashMap<>();
        this.map =  new HashMap<>();
    }

    public void put(K key, V value){
        map.put(key, value);
        reverseMap.put(value, key);
    }

    public void removeByKey(K key){
        V value = map.get(key);
        map.remove(key);
        reverseMap.remove(value);
    }

    public void removeByValue(V value){
        K key = reverseMap.get(value);
        reverseMap.remove(value);
        map.remove(key);
    }

    public V getValueByKey(K key){
        return map.get(key);
    }

    public K getKeyByValue(V value){
        return reverseMap.get(value);
    }

    public int size(){
        return map.size();
    }
}
