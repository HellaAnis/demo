package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private Environment env;

    public static String name;
    public static String word;

    @Override
    public void run(String... args)  {

        log.info("{}", env.getProperty("word.to.match"));
        log.info("{}", env.getProperty("file.name"));
        name = env.getProperty("file.name");
        word = env.getProperty("word.to.match");
        log.info("{}", "Variables loaded");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        outPutToFile(readFile(name, word));
    }


    public static List<String> readFile(String fileName, String wordToMatch) {
        System.out.println("fileName = " + fileName);
        List<String> list = new ArrayList<>();
        try {
            list =
                    Files.lines(new File(fileName)
                            .toPath()).filter(s -> s.equals(wordToMatch))
                            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void outPutToFile(List<String> result) {
        String fileName = "outPut.txt";
        System.out.println("fileName = " + fileName);
        File file = new File(fileName);
        file.delete();
        result.forEach(s -> {
            try {
                Files.write(Paths.get(file.getPath()),
                        (s + System.lineSeparator()).getBytes(),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
