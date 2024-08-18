package com.superhero.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class WikimediaChangeHandler implements EventHandler
{

	KafkaEventProducer kafkaEventProducer;

	private final Logger log = LoggerFactory.getLogger(WikimediaChangeHandler.class.getSimpleName());

	public WikimediaChangeHandler(KafkaEventProducer kafkaEventProducer)
	{
		this.kafkaEventProducer = kafkaEventProducer;
	}


	@Override
	public void onOpen()
	{
		// nothing here
	}

	@Override
	public void onClosed()
	{

	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
		//log.info(messageEvent.getData());
		this.kafkaEventProducer.sendWikiMediaEvents(CustomEvent.builder().name(messageEvent.getData()).build());
	}

	@Override
	public void onComment(String comment)
	{
		// nothing here
	}

	@Override
	public void onError(Throwable t)
	{
		log.error("Error in Stream Reading", t);
	}
}
