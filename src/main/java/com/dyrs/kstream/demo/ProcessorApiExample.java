package com.dyrs.kstream.demo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class ProcessorApiExample {
    public static void main(String[] args) {
        Topology topology = new Topology();

        topology.addSource("source", "locations")
                .addProcessor("print", SayHelloProcessor::new, "source");
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev-consumer");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.10.247:29092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // build the topology and start streaming!
        KafkaStreams streams = new KafkaStreams(topology, config);
        System.out.println("Starting streams");
        streams.start();

        // close Kafka Streams when the JVM shuts down (e.g. SIGTERM)
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
