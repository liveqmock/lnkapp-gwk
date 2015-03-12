package org.fbi.gwk.internal;

import org.fbi.gwk.tpsserver.hdserver.HdNettyServer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * User: zhanrui
 * Date: 20150309
 */
public final class TpsServerService implements Runnable {
    private static final String PID = "linking.gwk.tpsserver.hd";

    private final BundleContext context;
    private boolean running;
    private Thread thread;
    private ServiceRegistration configServiceReg;
    private HdNettyServer server;

    public TpsServerService(BundleContext context) {
        this.context = context;
    }

    public void start() {
        this.thread = new Thread(this, PID);
        this.thread.start();
    }

    public void stop() throws Exception {
        if (this.configServiceReg != null) {
            this.configServiceReg.unregister();
        }

        this.running = false;
        this.thread.interrupt();

        try {
            this.thread.join(3000);
        } catch (InterruptedException e) {
            //
        }
    }

    @Override
    public void run() {
        this.running = true;
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

        while (this.running) {
            startNetty();

            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    //
                }
            }
            stopNetty();
        }
    }


    private void startNetty() {
        try {
            this.server = new HdNettyServer();
            this.server.run();
        } catch (Exception e) {
            //TODO SystemLogger.error
        }
    }

    private void stopNetty() {
        if (this.server != null) {
            try {
                this.server.stop();
                this.server = null;

                System.out.println("Netty tpsserver stopped.............");

            } catch (Exception e) {
                //TODO SystemLogger.error
            }
        }
    }

}
