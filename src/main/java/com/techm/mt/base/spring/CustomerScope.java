package com.techm.mt.base.spring;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Created by cjavellana on 13/9/15.
 */
public class CustomerScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return null;
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
