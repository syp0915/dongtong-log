package com.shfc.log;

import com.shfc.kafka.producer.KafkaMessageProducerClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/31 下午8:51.
 */
public class KafkaTest extends JunitBaseTest {
    @Autowired(required = false)
    private KafkaMessageProducerClient kafkaMessageProducerClient;

    @Test
    public void testKafka(){
        kafkaMessageProducerClient.asyncSend("duzi", System.currentTimeMillis() + "");
    }
}
