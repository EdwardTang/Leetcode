package com.company.vmware.finddup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Leetcode 609. Find Duplicate File in System */
class Solution {
  // High level:
  // 1. split the string into: pair of <content : dir + fileName>
  // 2. use content as key, group all the pair into a hashmap:  Map<content, List <dir + fileName>>
  // 3. add the values of above hash map entries to a List<List<String>> result
  public List<List<String>> findDuplicate(String[] paths) {
    List<List<String>> result = new ArrayList<>();
    if (paths == null | paths.length == 0) {
      return result;
    }
    Map<String, List<String>> map = new HashMap<>();

    for (String folder : paths) {
      String[] folderSubstr = folder.split(" ");
      mapAndGroup(map, folderSubstr);
    }
    map.entrySet().stream()
        .filter(e -> (e.getValue() != null && e.getValue().size() > 1))
        .forEach(
            e -> {
              result.add(e.getValue());
            });
    return result;
  }

  private void mapAndGroup(Map<String, List<String>> map, String[] s) {
    String dir = s[0];\
    for (int i = 1; i < s.length; i++) {
      String[] metaData = s[i].split("\\(");
      String fileName = metaData[0];
      String content = metaData[1].substring(0, metaData[1].length() - 1);
      String file = dir + "/" + fileName;
      if (!map.containsKey(content)) {
        map.put(content, new ArrayList<>());
      }
      map.get(content).add(file);
    }
  }
}
