package com.example.exception;

public class ExceptionHandling {
    public static void main(String[] args) {

        try {
            int myInt = Integer.parseInt("hello");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally");
        }
    }
}
