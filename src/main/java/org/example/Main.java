package org.example;

import modelo.HttpServerWithShutdown;

public class Main {
    public static void main(String[] args)
    {
       HttpServerWithShutdown httpServerWithShutdown = new HttpServerWithShutdown();
        try {
            httpServerWithShutdown.iniciar();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }


    }
}