package org.nanocontainer.boot;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URL;
import java.security.AccessControlException;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class BrownBear {
    public BrownBear(Honey honey, Class klass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        try {
            new Socket("google.com", 80);
            System.out.println("BrownBear: 'socket open' NOT blocked to google.com:80 (wrong)");
        } catch (AccessControlException e) {
            System.out.println("BrownBear: 'socket open' blocked to google.com:80 (correct)");
        } catch (IOException e) {
            e.printStackTrace();
        }


//        ClassLoader cl = this.getClass().getClassLoader();
//        System.out.println("--> cl1 " + cl);
//        cl = cl.getParent();
//        if (cl != null) {
//            System.out.println("--> cl2 " + cl);
//            cl = cl.getParent();
//            if (cl != null) {
//                System.out.println("--> cl3 " + cl);
//                cl = cl.getParent();
//                if (cl != null) {
//                    System.out.println("--> cl4 " + cl);
//                    cl = cl.getParent();
//                    if (cl != null) {
//                        System.out.println("--> cl5 " + cl);
//                        cl = cl.getParent();
//                        if (cl != null) {
//                        }
//                    }
//                }
//            }
//        }

        try {
            new Socket("yahoo.com", 80);
            System.out.println("BrownBear: 'socket open' NOT blocked to yahoo.com:80 (correct)");
        } catch (AccessControlException e) {
            System.out.println("BrownBear: 'socket open' blocked to yahoo.com:80 (wrong)");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            klass.getClassLoader();
            System.out.println("BrownBear: Can access classloader of class *not* in BrownBear's tree (wrong)");
        } catch (AccessControlException e) {
            System.out.println("BrownBear: Can't access classloader of class *not* in BrownBear's tree (correct)");
        }

        this.getClass().getClassLoader();
        System.out.println("BrownBear: Can access classloader of this class (correct)");

        System.out.println("BrownBear: I have eaten " + honey.eatSome() + " calories of Honey (of unknown type)");
        Class clazz = null;
        try {
            clazz = this.getClass().getClassLoader().loadClass("org.nanocontainer.boot.BeeHiveHoney");
        } catch (ClassNotFoundException cnfe) {
        }
        System.out.println("BrownBear: Can see class for BeeHiveHoney ? - " + (clazz != null));
        System.out.println("BrownBear: honey instance's class type - " + honey.getClass());
        Method nonInterfaceMethod = null;
        try {
            nonInterfaceMethod = honey.getClass().getMethod("nonInterfaceMethod", new Class[0]);
        } catch (NoSuchMethodException exception) {
        }
        System.out.println("BrownBear: Can see honey instance's 'nonInterfaceMethod'? - " + (nonInterfaceMethod != null));
        boolean invoked = false;
        try {
            nonInterfaceMethod.invoke(honey, new Object[0]);
            invoked = true;
        } catch (Exception e) {
        }
        System.out.println("BrownBear: Can invoke honey instance's 'nonInterfaceMethod'? - " + invoked);
        nonInterfaceMethod = null;
        if (clazz != null) {
            nonInterfaceMethod = clazz.getMethod("nonInterfaceMethod", new Class[0]);
        }
        System.out.println("BrownBear: Can see HoneyBeeHoney.nonInterfaceMethod()? - " + (nonInterfaceMethod != null));
        invoked = false;
        try {
            nonInterfaceMethod.invoke(honey, new Object[0]);
            invoked = true;
        } catch (Exception e) {
        }
        System.out.println("BrownBear: Can invoke HoneyBeeHoney class' 'nonInterfaceMethod' against honey's instance? - " + invoked);
        System.out.println("BrownBear: Can leverage any implementation detail from honey instance? - false");

    }
}