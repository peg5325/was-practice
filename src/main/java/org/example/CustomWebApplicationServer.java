package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", 8080);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null){
                logger.info("[CustomWebApplicationServer] client connected!");

                /**
                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 * 한계점 : 클라이언트의 요청이 들어왔을 때, 해당하는 작업을 메인 Thread가 처리한다는 점.
                 *
                 * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 * 이슈 : Thread는 생성될 때 마다, 독립적인 스택 메모리를 할당받는데, 이는 매우 비싼 작업임.
                 *       성능이 매우 떨어짐. 요청이 많아지면 그만큼 Thread를 생성.
                 *
                 * Step3 - Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
