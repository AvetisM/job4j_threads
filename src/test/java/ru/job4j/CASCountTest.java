package ru.job4j;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CASCountTest {

    @Test
    public void whenIncrement3times() {

        CASCount casCount = new CASCount(0);
        casCount.increment();
        assertThat(casCount.get(), is(1));
        casCount.increment();
        assertThat(casCount.get(), is(2));
        casCount.increment();
        assertThat(casCount.get(), is(3));

    }

    @Test
    public void whenInitialValueNotZero() {
        CASCount casCount = new CASCount(2);
        casCount.increment();
        casCount.increment();
        casCount.increment();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get(), is(7));
    }

    @Test
    public void whenInitialValueLessThanZero() {
        CASCount casCount = new CASCount(-1);
        casCount.increment();
        casCount.increment();
        casCount.increment();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get(), is(4));
    }
}