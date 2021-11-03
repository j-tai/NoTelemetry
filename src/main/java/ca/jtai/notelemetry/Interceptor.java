package ca.jtai.notelemetry;

import java.security.Permission;

public class Interceptor extends SecurityManager {
    @Override
    public void checkConnect(String host, int port) {
        new Exception("Tried to connect to '" + host + "' port " + port).printStackTrace();
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
        checkConnect(host, port);
    }

    @Override
    public void checkPermission(Permission perm) {
        if ("setSecurityManager".equals(perm.getName())) {
            throw new SecurityException("Cannot replace the security manager");
        }
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        checkPermission(perm);
    }

    public static void install() {
        try {
            System.setSecurityManager(new Interceptor());
        } catch (SecurityException e) {
            new IllegalStateException("Failed to install interceptor", e).printStackTrace();
        }
    }
}
