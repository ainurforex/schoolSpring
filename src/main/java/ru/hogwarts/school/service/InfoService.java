package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {
    Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Value("${server.port}")
    private Integer serverPort;

    public Integer getPort() {
        logger.info("Was invoked method for getPort");
        return serverPort;
    }

    public Integer getNumber() {
        logger.info("Was invoked method for getNumber");
        int sum = Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel().reduce(1, (a, b) -> a + b);
        return sum;
    }


}
