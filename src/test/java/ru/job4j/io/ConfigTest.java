package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }
    @Test
    void whenOnlyKey() {
        String path = "./data/pair_only_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ошибка шаблона пары ключ - значение");

    }
    @Test
    void whenOnlyValue() {
        String path = "./data/pair_only_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ошибка шаблона пары ключ - значение");
    }

    @Test
    void whenNoKeyAndValue() {
        String path = "./data/pair_no_key_and_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ошибка шаблона пары ключ - значение");
    }

    @Test
    void whenEquals() {
        String path = "./data/no_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ошибка шаблона пары ключ - значение");
    }

    @Test
    void whenDoubleProperties() {
        String path = "./data/double_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect=1");
    }
}