package com.c3p0;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.BeforeClass;
import org.junit.Test;
import com.c3po.C3p0Source;

public class C3p0SourceTest {
    int size = 4;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void getConnection() {
        new Thread(new Runnable() {
            public void run() {
                for (int y = 1; y < size; y++) {
                    for (int i = 1; i < 10; i++) {
                        Connection connection = C3p0Source.getConnection();
                        System.out.println("Thread-----" + i + "-----" + connection);
                        try {
                            C3p0Source.realseSource(null, null, connection);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

        for (int y = 1; y < size; y++) {
            for (int i = 1; i < 10; i++) {
                Connection connection = C3p0Source.getConnection();
                System.out.println(i + "-----" + connection);
                try {
                    C3p0Source.realseSource(null, null, connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}