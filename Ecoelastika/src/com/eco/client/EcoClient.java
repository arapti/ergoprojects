package com.eco.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.ConnectTimeoutException;

public class EcoClient {

	public static final String BASE_URI = "";

	public static final String PATH_TRNID = "";

	public static void main(String[] args) throws UnsupportedEncodingException,
			ConnectTimeoutException, FileNotFoundException, IOException {

		File file = new File("/C:/Users/arapti/workspace/logs.txt");
		PrintStream printStream = new PrintStream(new FileOutputStream(file));
		System.setOut(printStream);// this turns output from console to the file
									// specified above

		URL url = null;
		/************************************ 1st call-devices ********************/
		try {

			url = new URL("http://localhost:62036/api/device/get");
			URLConnection conn = url.openConnection();

			conn.setDoOutput(true);
			conn.setConnectTimeout(60000);

			String line = "";

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();

			System.out.println("1 OK");
		} catch (Exception e) {
			System.out.println("1 Error");
			System.exit(1);
		}
		/************************************ 2nd call-data **************************/
		try {
			url = new URL("http://localhost:62036/api/data/get");
			URLConnection conn2 = url.openConnection();

			// logger.info("Sending to Middleware the following Request: " +
			// url);
			conn2.setDoOutput(true);
			conn2.setConnectTimeout(60000);

			String line2 = "";

			StringBuilder buffer2 = new StringBuilder();
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(
					conn2.getInputStream()));

			while ((line2 = reader2.readLine()) != null) {
				buffer2.append(line2);
			}

			reader2.close();
			System.out.println("2 OK");
		} catch (Exception e) {

			System.out.println("2 Error");
			System.exit(1);
		}

		/************************************ 3rd call - stations ******************/
		try {
			url = new URL("http://localhost:62036/api/station/get");
			URLConnection conn3 = url.openConnection();

			// logger.info("Sending to Middleware the following Request: " +
			// url);
			conn3.setDoOutput(true);
			conn3.setConnectTimeout(60000);

			String line3 = "";

			StringBuilder buffer3 = new StringBuilder();
			BufferedReader reader3 = new BufferedReader(new InputStreamReader(
					conn3.getInputStream()));

			while ((line3 = reader3.readLine()) != null) {
				buffer3.append(line3);
			}

			reader3.close();
			System.out.println("3 OK");
		} catch (Exception e) {
			System.out.println("3 Error");
			System.exit(1);
		}

		/************************************ 4th call - vehicles ******************/
		try {
			url = new URL("http://localhost:62036/api/vehicle/get");
			URLConnection conn4 = url.openConnection();

			// logger.info("Sending to Middleware the following Request: " +
			// url);
			conn4.setDoOutput(true);
			conn4.setConnectTimeout(60000);

			String line4 = "";

			StringBuilder buffer4 = new StringBuilder();
			BufferedReader reader4 = new BufferedReader(new InputStreamReader(
					conn4.getInputStream()));

			while ((line4 = reader4.readLine()) != null) {
				buffer4.append(line4);
			}

			reader4.close();
			System.out.println("4 OK");
		} catch (Exception e) {

			System.out.println("4 Error");
			System.exit(1);
		}

	}

}