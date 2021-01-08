package com.horizon.networking;

import java.io.IOException;

public class NetRunnable implements Runnable {
    private Client c;
    private Executioner e;
    private String msg = "";
    private static String id = "";
    private static String name = "";
    public static boolean a = false;
    public static Executioner.Actions msgact = null;

    public static void pair(String idInfo, String nameInfo) {
        id = idInfo;
        name = nameInfo;
    }

    @Override
    public void run() {
        try {
            c = new Client("192.168.1.34", 1690);
            e = new Executioner(c);
            a = e.sync(id, name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (a) {
            if (msgact != null) {
                System.out.println("blah");
                try {
                    e.Execute(msgact);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    msgact = null;
                }
            }

            try {
                msg = c.recieve();
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            } finally {
                if (msg != "") {
                    System.out.println("I GOT THIS " + msg);
                }
            }
        }

    }
}