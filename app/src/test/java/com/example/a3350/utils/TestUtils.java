package com.example.a3350.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import com.example.a3350.application.Main;

public class TestUtils {
    private static final File DB_SRC = new File("data/user/0/com.example.a3350/app_db/SC.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
