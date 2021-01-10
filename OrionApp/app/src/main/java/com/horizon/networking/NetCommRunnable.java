package com.horizon.networking;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.horizon.utils.SharedData;

import java.io.IOException;

public class NetCommRunnable implements Runnable {
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
            c = new Client();
            e = new Executioner(c);
            a = e.sync(id, name);

//            AppCompatActivity appCompatActivity = new AppCompatActivity() {
//                @Override
//                public void onCreate(@Nullable Bundle savedInstanceState,
//                                     @Nullable PersistableBundle persistentState) {
//                    SharedData.getInstance(this).setIsPaired(a);
//                }
//            };

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