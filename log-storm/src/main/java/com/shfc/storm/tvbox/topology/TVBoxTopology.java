package com.shfc.storm.tvbox.topology;

import com.shfc.storm.bean.MessageScheme;
import com.shfc.storm.env.PropertiesValueUtils;
import com.shfc.storm.tvbox.bolts.TVBoxFilterBolts;
import com.shfc.storm.tvbox.bolts.TVBoxSendBolts;
import com.shfc.storm.tvbox.bolts.TVBoxTransferJSONBolts;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/29 上午11:17.
 */
public class TVBoxTopology {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:config/application-*.xml");
        TVBoxSendBolts tvBoxSendBolts = (TVBoxSendBolts)applicationContext.getBean(TVBoxSendBolts.class);

        String zkHost  = PropertiesValueUtils.getStringPropertyValue("log.storm.zk.host");
        String kafkaHost = PropertiesValueUtils.getStringPropertyValue("log.storm.kafka.host");
        String topicsName = PropertiesValueUtils.getStringPropertyValue("log.storm.tvbox.kafka.topics");
        String zkRoot = PropertiesValueUtils.getStringPropertyValue("log.storm.tvbox.zk.zkroot");


        BrokerHosts brokerHosts = new ZkHosts(zkHost);
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topicsName, zkRoot, "tvbox_topology");
        Config config = new Config();
        Map<String, String> map = new HashMap<String, String>();
        map.put("metadata.broker.list", kafkaHost);
        map.put("serializer.class", "kafka.serializer.StringEncoder");
        config.put("kafka.broker.properties", map);
        config.setDebug(true);

        spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("tvbox-spout", new KafkaSpout(spoutConfig), 1);
        builder.setBolt("filter-bolt", new TVBoxFilterBolts()).shuffleGrouping("tvbox-spout");
        builder.setBolt("json-bolt", new TVBoxTransferJSONBolts()).shuffleGrouping("filter-bolt");
        builder.setBolt("send-bolt", new TVBoxSendBolts()).shuffleGrouping("json-bolt");
//        builder.setBolt("save-db-bolt", tvBoxSendBolts).shuffleGrouping("send-bolt");

        if (args != null && args.length > 0) {
            //提交到集群运行
            try {
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //本地模式运行
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("tvbox_topology", config, builder.createTopology());
        }
    }
}
