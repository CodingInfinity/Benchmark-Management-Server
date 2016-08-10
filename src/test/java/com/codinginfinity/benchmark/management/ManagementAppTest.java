package com.codinginfinity.benchmark.management;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ManagementApp.class)
public class ManagementAppTest {

    @Test
    public void managementAppMainWellDefinedTest() {
        Class clazz = ManagementApp.class;

        /* Confirm that all methods in the main class are declared as static methods */
        List<Method> methods = Arrays.asList(clazz.getMethods());
        methods.forEach(method -> {
            if (!Modifier.isStatic(method.getModifiers()) && method.getDeclaringClass().equals(clazz))
                assert false;
        });

        /* Confirm that all fields in the main class are declared as static fields */
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        fields.forEach(field -> {
            if (!Modifier.isStatic(field.getModifiers()) && field.getDeclaringClass().equals(clazz))
                assert false;
        });
    }

    @Test
    public void managementAppMainTest() throws Exception {
        SpringApplication application = Mockito.mock(SpringApplication.class);
        ConfigurableApplicationContext context = Mockito.mock(ConfigurableApplicationContext.class);
        ConfigurableEnvironment environment = Mockito.mock(ConfigurableEnvironment.class);

        PowerMockito.whenNew(SpringApplication.class).withAnyArguments().thenReturn(application);
        Mockito.when(application.run(Mockito.anyVararg())).thenReturn(context);
        Mockito.when(context.getEnvironment()).thenReturn(environment);
        Mockito.when(environment.getProperty(Mockito.anyString())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        ManagementApp.setApplication(application);
        ManagementApp.main(new String[] {"test1", "test2"});

        PowerMockito.verifyNew(SpringApplication.class).withArguments(ManagementApp.class);
        Mockito.verify(application, Mockito.times(1)).run("test1", "test2");
    }
}
