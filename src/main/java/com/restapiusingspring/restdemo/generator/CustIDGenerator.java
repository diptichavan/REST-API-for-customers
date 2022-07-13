package com.restapiusingspring.restdemo.generator;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

import java.util.concurrent.atomic.AtomicLong;

public class CustIDGenerator implements IdentifierGenerator {

    private static final AtomicLong LAST_TIME_MS = new AtomicLong(Long.MIN_VALUE);
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        /*long timestamp = System.currentTimeMillis();

        while (true) {
            long lastTime = LAST_TIME_MS.get();
            if (lastTime >= timestamp)
                timestamp = lastTime + 1;
            if (LAST_TIME_MS.compareAndSet(lastTime, timestamp))
                return timestamp;

            System.out.println("Timestamp" + timestamp);
            String id = Long.toString(timestamp);
            return id;
        }*/


            long expect, next = System.currentTimeMillis();
            do {
                expect = LAST_TIME_MS.get();
            } while(!LAST_TIME_MS.compareAndSet(expect, next));

            String id = Long.toString(next);
            return id;

    }
}