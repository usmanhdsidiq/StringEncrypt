/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.stringencrypt;

/**
 *
 * @author Usman H S
 */
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringEncrypt {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String in;

        System.out.println("Encrypt");
        System.out.print("Input: ");
        in = input.nextLine();

        // Convert to hex
        StringBuilder hexString = new StringBuilder();
        // String hexResult = hexString.toString();
        for (char c : in.toCharArray()) {
            String hex = Integer.toHexString((int) c);
            hexString.append(hex);
        }

        // Convert to binary
        StringBuilder binaryString = new StringBuilder();
        for (char c : hexString.toString().toCharArray()) {
            String binary = Integer.toBinaryString((int) c);
            String binaryOut = String.format("%8s", binary).replace(' ', '0');

            binaryString.append(binaryOut);
        }

        // convert to SHA-256
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(binaryString.toString().getBytes());

            StringBuilder hexStringA = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexStringA.append('0');
                }
                hexStringA.append(hex);
            }
            // return hexStringA.toString();
            System.out.println("Final result: " + hexStringA);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
