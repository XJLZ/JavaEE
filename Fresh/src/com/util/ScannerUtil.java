package com.util;

import java.util.Scanner;

public class ScannerUtil {
    private Scanner in = new Scanner(System.in);
    private static ScannerUtil instance = null;

    public static ScannerUtil getInstance() {
        if(instance == null){
            instance = new ScannerUtil();
        }
        return instance;
    }

    public ScannerUtil() {}

    @Override
    protected void finalize() throws Throwable {
        in.close();
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
    }


}