package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnalysisTest {
    @Test
    void whenOneTimeError(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("400 10:57:01");
            out.println("300 10:58:01");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis checkServer = new Analysis();
        checkServer.unavailable(source.getPath(), target.getPath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:58:01;").isEqualTo(rsl.toString());
    }

    @Test
    void whenNoError(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:58:01");
            out.println("300 10:58:01");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis checkServer = new Analysis();
        checkServer.unavailable(source.getPath(), target.getPath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertTrue(rsl.isEmpty());
    }

    @Test
    void whenCoupleTimesError(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        File target = tempDir.resolve("target.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("400 10:57:01");
            out.println("300 10:58:01");
            out.println("500 10:59:01");
            out.println("300 11:00:01");
        }
        Analysis checkServer = new Analysis();
        checkServer.unavailable(source.getPath(), target.getPath());
        StringBuilder rslTarget = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rslTarget::append);
        }
        assertThat("10:57:01;10:58:01;10:59:01;11:00:01;").isEqualTo(rslTarget.toString());
    }
}