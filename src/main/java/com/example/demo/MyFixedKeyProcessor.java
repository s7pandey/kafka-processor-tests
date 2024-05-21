package com.example.demo;

import org.apache.kafka.streams.processor.api.FixedKeyProcessor;
import org.apache.kafka.streams.processor.api.FixedKeyProcessorContext;
import org.apache.kafka.streams.processor.api.FixedKeyRecord;


public class MyFixedKeyProcessor<String, Integer> implements
    FixedKeyProcessor<String, String, Integer> {

  private FixedKeyProcessorContext<String, Integer> context;

  @Override
  public void init(FixedKeyProcessorContext<String, Integer> context) {
    this.context = context;
  }

  @Override
  public void process(FixedKeyRecord<String, String> fixedKeyRecord) {
    context.forward((FixedKeyRecord<String, Integer>) fixedKeyRecord.withValue(10));
  }

  @Override
  public void close() {
    FixedKeyProcessor.super.close();
  }
}
