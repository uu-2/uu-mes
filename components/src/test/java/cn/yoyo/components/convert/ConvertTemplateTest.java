package cn.yoyo.components.convert;

import cn.yoyo.components.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = TestApplication.class)
public class ConvertTemplateTest {

    @Autowired
    private TestConvert testConvert;

    @Test
    void convert() {
        E e = E.builder().name("test").age(1).build();
        T t = testConvert.e2t(e);
        assertEquals(e.getName(), t.getName());
        assertEquals(e.getAge(), t.getAge());
    }
}