package br.xksoberbado.mongodbshardexample.runner;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    protected void run(final Runnable runnable) {
        runnable.run();
    }
}
