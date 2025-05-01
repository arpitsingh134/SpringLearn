package com.java.learn;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Q11 {
    public static void main(String[] args) {
        try {
            Path resourcePath = Paths.get(Q11.class.getClassLoader().getResource("NewFile.txt").toURI());
            // Convert resource to URI then to Path
            String content = Files.readString(resourcePath);

            System.out.println(content);
            // Read using Files and Path (NIO)

            Files.writeString(resourcePath, content+"hello new file1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}