package com.scriptrix.engine.project;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ProjectSettings implements Map<String, Object> {

  private Map<String, Object> settings = new HashMap<>();

  @Override
  public int size() {
    return settings.size();
  }

  @Override
  public boolean isEmpty() {
    return settings.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return settings.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return settings.containsValue(value);
  }

  @Override
  public Object get(Object key) {
    return settings.get(key);
  }

  @Override
  public Object put(String key, Object value) {
    return settings.put(key, value);
  }

  @Override
  public Object remove(Object key) {
    return settings.remove(key);
  }

  @Override
  public void putAll(Map<? extends String, ?> m) {
    settings.putAll(m);
  }

  @Override
  public void clear() {
    settings.clear();
  }

  @Override
  public Set<String> keySet() {
    return settings.keySet();
  }

  @Override
  public Collection<Object> values() {
    return settings.values();
  }

  @Override
  public Set<Entry<String, Object>> entrySet() {
    return settings.entrySet();
  }

  @Override
  public boolean equals(Object o) {
    return settings.equals(o);
  }

  @Override
  public int hashCode() {
    return settings.hashCode();
  }

  @Override
  public Object getOrDefault(Object key, Object defaultValue) {
    return settings.getOrDefault(key, defaultValue);
  }

  @Override
  public void forEach(BiConsumer<? super String, ? super Object> action) {
    settings.forEach(action);
  }

  @Override
  public void replaceAll(BiFunction<? super String, ? super Object, ?> function) {
    settings.replaceAll(function);
  }

  @Override
  public Object putIfAbsent(String key, Object value) {
    return settings.putIfAbsent(key, value);
  }

  @Override
  public boolean remove(Object key, Object value) {
    return settings.remove(key, value);
  }

  @Override
  public boolean replace(String key, Object oldValue, Object newValue) {
    return settings.replace(key, oldValue, newValue);
  }

  @Override
  public Object replace(String key, Object value) {
    return settings.replace(key, value);
  }

  @Override
  public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
    return settings.computeIfAbsent(key, mappingFunction);
  }

  @Override
  public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
    return settings.computeIfPresent(key, remappingFunction);
  }

  @Override
  public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
    return settings.compute(key, remappingFunction);
  }

  @Override
  public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction) {
    return settings.merge(key, value, remappingFunction);
  }
}
