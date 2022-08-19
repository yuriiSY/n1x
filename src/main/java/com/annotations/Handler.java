package com.annotations;

import com.repository.PhoneRepository;
import com.repository.TabletRepository;
import com.repository.TelevisionRepository;
import com.service.PhoneService;
import com.service.TabletService;
import com.service.TelevisionService;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Handler {

    Map<Class<?>, Object> cache;


    public Handler() {
        cache = new HashMap<>();
    }


    public Map<Class<?>, Object> createCache() {
        Reflections reflections = new Reflections("com");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MySingleton.class);
        cacheRep(annotatedClasses);
        cacheService(annotatedClasses);
        return cache;
    }

    private void cacheRep(Set<Class<?>> annotatedClasses) {
        annotatedClasses.forEach(aClass -> {
            Arrays.stream(aClass.getDeclaredConstructors())
                    .forEach(constructor -> {
                        if (constructor.isAnnotationPresent(MyAutowired.class) && constructor.getParameterCount() == 0) {
                            try {
                                constructor.setAccessible(true);
                                Object o = constructor.newInstance();
                                cache.put(aClass, o);
                            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        });
    }

    private void cacheService(Set<Class<?>> annotatedClasses) {
        annotatedClasses.forEach(aClass -> {
            Arrays.stream(aClass.getDeclaredConstructors())
                    .forEach(constructor -> {
                        if (constructor.isAnnotationPresent(MyAutowired.class)) {
                            try {
                                if (PhoneService.class.equals(aClass)) {
                                    constructor.setAccessible(true);
                                    Object o = constructor.newInstance(cache.get(PhoneRepository.class));
                                    cache.put(aClass, o);
                                }
                                if (TabletService.class.equals(aClass)) {
                                    constructor.setAccessible(true);
                                    Object o = constructor.newInstance(cache.get(TabletRepository.class));
                                    cache.put(aClass, o);
                                }
                                if (TelevisionService.class.equals(aClass)) {
                                    constructor.setAccessible(true);
                                    Object o = constructor.newInstance(cache.get(TelevisionRepository.class));
                                    cache.put(aClass, o);
                                }
                            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        });
    }
}

