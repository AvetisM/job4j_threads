package ru.job4j.cash;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        accounts.putIfAbsent(account.id(), account);
        return true;
    }

    public synchronized boolean update(Account account) {
        accounts.put(account.id(), account);
        return true;
    }

    public synchronized boolean delete(int id) {
        accounts.remove(id);
        return true;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rls = false;
        Optional<Account> fromAccount = getById(fromId);
        Optional<Account> toAccount = getById(toId);
        if (fromAccount.isPresent()
                && toAccount.isPresent()
                && fromAccount.get().amount() >= amount) {
            fromAccount.get().setAmount(fromAccount.get().amount() - amount);
            toAccount.get().setAmount(toAccount.get().amount() + amount);
            rls = true;
        }
        return rls;
    }
}
