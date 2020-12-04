#!/usr/bin/groovy
package org.util

class CacheUtil {
  static String sanitizeCacheKey(String cacheKey) {
    return cacheKey.replaceAll("\\W", "-");
  }
}
