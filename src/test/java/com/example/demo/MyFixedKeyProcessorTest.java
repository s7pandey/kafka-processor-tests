package com.example.demo;

import java.time.Instant;
import java.util.Objects;
import org.apache.kafka.streams.processor.api.FixedKeyRecord;
import org.apache.kafka.streams.processor.api.InternalFixedKeyRecordFactory;
import org.apache.kafka.streams.processor.api.MockFixedKeyProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.junit.jupiter.api.Test;

public class MyFixedKeyProcessorTest {

  @Test
  void test() {
    MyFixedKeyProcessor<String, Integer> processor = new MyFixedKeyProcessor<>();
    MockFixedKeyProcessorContext<String, Integer> context = new MockFixedKeyProcessorContext<>();
    FixedKeyRecord<String, String> recordToSend = InternalFixedKeyRecordFactory.create(new Record<>("key", "value",
        Instant.now().toEpochMilli()));
    processor.init(context);
    processor.process(recordToSend);

    var output = context.forwarded().get(0).fixedKeyRecord();
    var expectedOutput = recordToSend.withValue(10);
    assert Objects.equals(output, expectedOutput);



  }

}
