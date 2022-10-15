package ru.job4j;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CASCountTest {

    @Test
    public void whenIncrement3times() {

        CASCount casCount = new CASCount();
        casCount.increment();
        assertThat(casCount.get(), is(1));
        casCount.increment();
        assertThat(casCount.get(), is(2));
        casCount.increment();
        assertThat(casCount.get(), is(3));

    }

}