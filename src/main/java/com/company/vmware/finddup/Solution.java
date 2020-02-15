package com.company.vmware.finddup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode 609. Find Duplicate File in System
 */
class Solution {
  public List<List<String>> findDuplicate(String[] paths) {
    List<List<String>> result = new ArrayList<>();
    if(paths == null | paths.length == 0) {
      return result;
    }
    Map<String, List<String>> map = new HashMap<>();
    // 1. split the string into: parir of <content : dir + file name>
    // 2. use content as key, group all the pair into a hashmap:  Map<content ,List <dir + file name>>
    // 3. add the values of above hash map entries to a List<List<String>> result
    for(String folder : paths) { // {"root/a 1.txt(abcd) 2.txt(efgh)"}
      String[] folderSubstr = folder.split(" ");// {"root/a”, “1.txt(abcd)”, “ 2.txt(efgh)”}
        mapAndGroup(map, folderSubstr);
      }
      map.entrySet().stream()
          .filter(e ->( e.getValue() != null && e.getValue().size() > 1))
          .forEach(e->{        // [“abcd” : {“root/a/1.txt”, “root/c/3.txt”},”efgh” : {“roo/c/3.txt”,
            // “root/c/d/4.txt”, “root/4.txt”} ]
            result.add(e.getValue());
          });
      return result; // [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
    }

    private void mapAndGroup(Map<String, List<String>> map,  String[] s) {
      String dir = s[0]; //"root/a”
      for(int i = 1; i < s.length; i++) {
        String[] metaData = s[i].split("\\("); //  {“1.txt”, “abcd)”}
        String fileName = metaData[0];   // “1.txt”
        String content = metaData[1].substring(0, metaData[1].length() - 1); // “abcd”
        String file = dir + "/" + fileName; // “root/a/1.txt”
        if(!map.containsKey(content)) {
          map.put(content, new ArrayList<>());
        }
        map.get(content).add(file);
        // [“abcd” : {“root/a/1.txt”}]
      }
    }
  }
