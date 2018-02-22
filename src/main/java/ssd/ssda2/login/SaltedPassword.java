package ssd.ssda2.login;

import java.security.SecureRandom;
import org.apache.catalina.realm.MessageDigestCredentialHandler;

public class SaltedPassword {

    final private static String symbols
            = "ABCDEFGHIJKMNPQRSTUVWXYZ"
            + "abcdefghijkmnpqrstuvwxyz"
            + "23456789";

    final private static int SALT_LENGTH = 32;
    final private static int NUM_OF_ITERATIONS = 100;
    final private static String ALGORITHM = "SHA-256";

    public static String random(int length) {

        StringBuilder buf = new StringBuilder(length);
        SecureRandom rand = new SecureRandom();

        for (int i = 0; i < length; ++i) {
            buf.append(symbols.charAt(rand.nextInt(symbols.length())));
        }

        return buf.toString();
    }

    public static boolean equals(String p1, String p2) {
        if (p1 != null && p2 != null) {
            return p1.equals(p2);
        } else {
            return false;
        }
    }

    public static String encode(String password) {

      MessageDigestCredentialHandler handler 
              = new MessageDigestCredentialHandler();
      
      try{
        handler.setAlgorithm(ALGORITHM);
      }catch(Exception e){
        throw new RuntimeException(e.getCause());
      }
      
      handler.setIterations(NUM_OF_ITERATIONS);
      handler.setSaltLength(SALT_LENGTH);
      handler.setEncoding("UTF-8");
      
      String code = handler.mutate(password);
      return code;
    }

    public static boolean match(String password, String code) {

      MessageDigestCredentialHandler handler 
              = new MessageDigestCredentialHandler();
      
      try{
        handler.setAlgorithm(ALGORITHM);
      }catch(Exception e){
        throw new RuntimeException(e.getCause());
      }
      
      handler.setIterations(NUM_OF_ITERATIONS);
      handler.setSaltLength(SALT_LENGTH);
      handler.setEncoding("UTF-8");
      
      return handler.matches(password, code);
    }

    public static void main(String[] args) {
        String password = "sesame";

        for (int i = 1; i <= 10; ++i) {
            String hash = encode(password);
            System.out.println();
            System.out.println("password " + i + ":");
            System.out.println("used password = [" + password + "]");
            System.out.println("stored password = [" + hash + "]");
            System.out.println("stored length = " + hash.length());
            System.out.println("matched = " + match(password, hash));
        }
    }

}
