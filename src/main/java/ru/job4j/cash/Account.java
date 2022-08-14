package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

@ThreadSafe
public class Account {
    @GuardedBy("this")
    private final int id;
    @GuardedBy("this")
    private int amount;

    Account(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public synchronized int id() {
        return id;
    }

    public synchronized int amount() {
        return amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Account) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account["
                + "id=" + id + ", "
                + "amount=" + amount + ']';
    }

}
