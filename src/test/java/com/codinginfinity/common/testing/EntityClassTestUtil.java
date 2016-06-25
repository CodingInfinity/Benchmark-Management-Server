package com.codinginfinity.common.testing;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by andrew on 2016/06/25.
 */
public final class EntityClassTestUtil extends Object {

    private static final Logger log = Logger.getLogger(EntityClassTestUtil.class.getName());

    public static void assertEntityClassWellDefined(Class<?> clazz) {

        /* Requirement: The class must be annotated with the javax.persistence.Entity annotation. */
        boolean correctAnnotation = false;
        for(Annotation annotation :  clazz.getAnnotations()) {
            if(annotation.annotationType().equals(javax.persistence.Entity.class)) {
                correctAnnotation = true;
                break;
            }
        }
        assert correctAnnotation;

        /* Requirement: The class must have a public or protected, no-argument constructor. The class may have other
            constructors. */
        Constructor[] constructors = clazz.getDeclaredConstructors();
        boolean correctConstructor = false;
        for (Constructor constructor : constructors) {
            if ((Modifier.isPublic(constructor.getModifiers()) || Modifier.isProtected(constructor.getModifiers()))
                && (constructor.getParameterCount() == 0)) {
                correctConstructor = true;
                break;
            }
        }

        assert correctConstructor;

        /* Requirement: The class must not be declared final. No methods or persistent instance variables must be
        declared final. */
        assert !Modifier.isFinal(clazz.getModifiers());
        Field[] fields = clazz.getDeclaredFields();
        Class cls = clazz;
        do {
            for (Field field : cls.getDeclaredFields()) {
                if (!field.getName().equals("serialVersionUID") &&
                        (Modifier.isTransient(field.getModifiers()) || Modifier.isFinal(field.getModifiers()))) {
                    assert false;
                }
            }
        } while ((cls = cls.getSuperclass()) != null);

        /* Requirement: If an entity instance be passed by value as a detached object, such as through a session bean’s
            remote business interface, the class must implement the Serializable interface. */
        boolean isSerializable = false;
        cls = clazz;
        do {
            for(Class interfaze : cls.getInterfaces()) {
                if(interfaze.getName().equals(java.io.Serializable.class.getName())) {
                    isSerializable = true;
                    break;
                }
            }
        } while ((cls = cls.getSuperclass()) != null && !isSerializable);
        assert isSerializable;

        /* Requirement: Persistent instance variables must be declared private, protected, or package-private, and can
            only be accessed directly by the entity class’s methods. Clients must access the entity’s state through
            accessor or business methods. */
        cls = clazz;
        boolean mappedSuperClass = false;
        do {
            for (Field field : cls.getDeclaredFields()) {
                int e = field.getModifiers();
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotation.annotationType().equals(javax.persistence.Transient.class)) {
                        break;
                    }
                }

                if (!(Modifier.isStatic(e) || Modifier.isFinal(e) || Modifier.isTransient(e))) {
                    if (!(Modifier.isPrivate(e) || Modifier.isProtected(field.getModifiers()))) {
                        assert false;
                    }
                }
            }
            for (Annotation annotation : cls.getAnnotations()) {
                if (annotation.annotationType().equals(javax.persistence.MappedSuperclass.class)) {
                    mappedSuperClass = true;
                    break;
                }
            }
            cls = cls.getSuperclass();
        } while (cls != null && !mappedSuperClass);
    }
}
