package com.dyrs.kstream.demo;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class SayHelloProcessor implements Processor<String, String> {
    @Override
    public void init(ProcessorContext context) {
    }

    @Override
    public void process(String key, String value) {
        System.out.println("(Processor API) Hello, " + value);
    }

    @Override
    public void close() {
    }
}