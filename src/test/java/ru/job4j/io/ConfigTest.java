package ru.job4j.io;

import org.assertj.core.api.ThrowableAssert;
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
        assertThatThrownBy(config.load()).isInstanceOf(IllegalArgumentException.class).hasMessage("ошибка шаблона пары ключ - значение");
    }
}