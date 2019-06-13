package io.pivotal.pde.UDPtester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.net.DatagramSocket;

@SpringBootApplication
public class UDPtester {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(UDPtester.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(UDPServer.class);
		context.register(UDPClient.class);
		context.refresh();

		UDPServer serverProcess = (UDPServer) context.getBean("UDPServer");
		UDPClient clientProcess = (UDPClient) context.getBean("UDPClient");

		int port_start;
		int port_stop;

		if (args.length == 2) {
			port_start = Integer.parseInt(args[0]);
			port_stop  = Integer.parseInt(args[1]);
			System.out.println();
			System.out.println("#################################################################################");
			System.out.println();
			System.out.println("Starting to connect on UDP port range " + port_start + " - " + port_stop);
			System.out.println();
			System.out.println("#################################################################################");
			Thread.sleep(4000);
		} else {
			port_start = 10000;
			port_stop = 10010;
			System.out.println("#################################################################################");
			System.out.println();
			System.out.println("No commandline arguments provided !!!");
			System.out.println();
			System.out.println("Try to run with two command line arguments like: ");
			System.out.println();
			System.out.println("        java -jar ./target/UDPtester-0.0.1-SNAPSHOT.jar 20000 20010");
			System.out.println();
			System.out.println("Starting to connect on default UDP port range " + port_start + " - " + port_stop);
			System.out.println();
			System.out.println("#################################################################################");
			Thread.sleep(4000);
		}

		for (int port = port_start; port <= port_stop ; port++) {
			try {

				DatagramSocket ds = serverProcess.initializeServe(port);

				clientProcess.sendPacket("Pivotal Greenplum Database is the best MPP platform, no doubts. ", port);
				serverProcess.receivePacket(ds);

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		System.out.println();
		System.out.println("#################################################################################");
		System.out.println();
		System.out.println("UDP connections of range " + port_start + " - " + port_stop + " has being opened.");
		System.out.println("Type Ctrl+C to quit application");
		System.out.println();
		System.out.println("#################################################################################");



	}


}
