package com.codinginfinity.common.testing;

import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by andrew on 2016/07/06.
 */
public final class ConstantClassTestUtil {

    private static final Logger log = Logger.getLogger(ConstantClassTestUtil.class.getName());

    public static void assertConstantClassWellDefined(Class<?> clazz) throws TestingException {

        assert Modifier.isFinal(clazz.getModifiers());

        assert clazz.getDeclaredConstructors().length == 1;

        try {
            Constructor e = clazz.getDeclaredConstructor(new Class[0]);
            if (e.isAccessible() || !Modifier.isPrivate(e.getModifiers())) {
                log.log(Level.SEVERE, "ConstantClassTestUtil: Constructor is not private", e);

                assert false;
            }

            e.setAccessible(true);
            e.newInstance(new Object[0]);
            e.setAccessible(false);
        } catch (NoSuchMethodException |
                IllegalAccessException |
                InvocationTargetException |
                InstantiationException e) {
            throw new TestingException(e);
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.isSynthetic()) {
                log.log(Level.SEVERE, "ConstantClassTestUtil: Class may not contain methods");
                assert false;
            }
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isSynthetic()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isFinal(modifiers) ||
                        !Modifier.isStatic(modifiers) ||
                        !Modifier.isPublic(modifiers)) {
                    log.log(Level.SEVERE, "ConstantClassTestUtil: All fields should be public final static fields");
                    assert false;
                }
            }
        }
    }
}
