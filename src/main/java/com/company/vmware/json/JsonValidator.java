package com.company.vmware.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;

public class JsonValidator {

  public  static void main(String[] args) {
    String test1 = "{}"; // true
    String test2 = "{";  //false
    String test3 = "{\"x\" : { \"y\" : \"z\"}}"; // true
    String test4 =
        "{ \n"
            + "   \"glossary\":{ \n"
            + "      \"title\":\"exampleglossary\",\n"
            + "      \"GlossDiv\":{ \n"
            + "         \"title\":\"S\",\n"
            + "         \"GlossList\":{ \n"
            + "            \"GlossEntry\":{ \n"
            + "               \"ID\":\"SGML\",\n"
            + "               \"SortAs\":\"SGML\",\n"
            + "               \"GlossTerm\":\"StandardGeneralizedMarkupLanguage\",\n"
            + "               \"Acronym\":\"SGML\",\n"
            + "               \"Abbrev\":\"ISO8879:1986\",\n"
            + "               \"GlossDef\":{ \n"
            + "                  \"para\":\"Ameta-markuplanguage,usedtocreatemarkuplanguagessuchasDocBook.\",\n"
            + "                  \"GlossSeeAlso\":[ \n"
            + "                     \"GML\",\n"
            + "                     \"XML\"\n"
            + "                  ]\n"
            + "               },\n"
            + "               \"GlossSee\":\"markup\"\n"
            + "            }\n"
            + "         }\n"
            + "      }\n"
            + "   }\n"
            + "}";
    String test5 = "[{\n" + "\t\"\": \"\"\n" + "}]";
    String test6 = "[\n" + "\t[]";
//    assertTrue(isValid(test1));
//    assertFalse(isValid(test2));
//    assertTrue(isValid(test3));
    assertTrue(isValid(test4));
    assertTrue(isValid(test5));
    assertFalse(isValid(test6));
  }

  public static boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    char[] chars = s.toCharArray();
    for(int i = 0; i < chars.length; i++) {
//      if(!stack.isEmpty() && stack.peekLast() == ':') {
//        if(chars[i] == '"' || chars[i] == '{' || chars[i] == '[' || Character.isDigit(chars[i])) {
//          if(chars[i] == '"' || chars[i] == '{' || chars[i] == '['){
//            stack.addLast(chars[i]);
//          } else if (Character.isDigit(chars[i])) {
//            if(chars[i] == '0'){
//              stack.addLast(chars[i]);
//            }
//          }
//        } else {
//          // {"x" : a }
//          System.out.println("processed: " + s.substring(0, i));
//          System.out.println("chars[i]: " + chars[i]);
//          return false;
//        }
//      } else if(!stack.isEmpty() && stack.peekLast() == '0') {
//        if(chars[i] != '.') {
//          System.out.println("processed: " + s.substring(0, i));
//          System.out.println("chars[i]: " + chars[i]);
//          return false;
//        } else {
//          stack.addLast(chars[i]);
//        }
//      } else if(!stack.isEmpty() && stack.peekLast() == '.') {
//        if(!Character.isDigit(chars[i])) {
//          return false;
//        }
//      } else if(isFocusSymbol(chars[i])) {
      if(isFocusSymbol(chars[i])) {
       if(stack.isEmpty()) {
         stack.addLast(chars[i]);
       } else {
         // stack is not empty
         char top = stack.peekLast();
         if(!isValidTop(top)) {
           System.out.println("processed: " + s.substring(0, i));
           System.out.println("chars[i]: " + chars[i]);
           return false;
         } else {
           // top can be , { [ " :
           if(top == '{') {
             if(chars[i] ==']') {
               System.out.println("processed: " + s.substring(0, i));
               System.out.println("chars[i]: " + chars[i]);
               return false;
             } else if (chars[i] == '}'){
               stack.removeLast();
             } else {
               stack.addLast(chars[i]);

             }
           } else if (top == '[') {
             if( chars[i] == ':' || chars[i] == '}') {
               System.out.println("processed: " + s.substring(0, i));
               System.out.println("chars[i]: " + chars[i]);
               return false;
             } else if (chars[i] == ']') {
               stack.removeLast();
             } else {
               stack.addLast(chars[i]);
             }
           } else if (top == ':' || top == ',') {
             if(chars[i] == ']' || chars[i] == '}' || chars[i] == ',' || chars[i] == ':') {
               System.out.println("processed: " + s.substring(0, i));
               System.out.println("chars[i]: " + chars[i]);
               return false;
             } else {
               //  if (chars[i] == '{' || chars[i] == '[' || chars[i] == '"')
               stack.removeLast();
               stack.addLast(chars[i]);
             }
           } else  {
             // if (top == '"')
             if(chars[i] == '"') {
               stack.removeLast();
             } else {
               // skip any character inside the quotation mark
               continue;
             }
           }
         }


       }
      } else {
        // skip literals
        continue;
      }
    }
    return stack.isEmpty();
  }

  private static boolean isFocusSymbol(char ch) {
    return (ch == '{' || ch == '}' || ch == '[' || ch == ']' || ch == ':' || ch == ',' || ch == '"');
  }

  private static boolean isValidTop(char ch) {
    return (ch == '{' || ch == '[' || ch == ':' || ch == ',' || ch == '"');
  }
}
