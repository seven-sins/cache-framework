package com.lonecpp.elasticsearch.test;

import java.io.IOException;
import java.net.InetAddress;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * @author seven sins
 * @date 2018年1月25日 下午9:30:11
 */
public class Test1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Settings settings = Settings.builder().put("cluster.name", "my-application").build();
		
		TransportClient client = new PreBuiltTransportClient(settings)
		        .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		
		prepareData(client);
		
		executeSearch(client);
		
		client.close();
	}

	private static void executeSearch(TransportClient client) {
		SearchResponse response = client.prepareSearch("company")
				.setTypes("employee")
				.setQuery(QueryBuilders.matchQuery("position", "technique"))
				.setPostFilter(QueryBuilders.rangeQuery("age").from(15).to(40))
				.setFrom(0).setSize(1)
				.get();
		SearchHit[] searchHits = response.getHits().getHits();
		for(int i = 0; i<searchHits.length; i++) {
			System.out.println(searchHits[i].getSourceAsString());
		}
	}

	private static void prepareData(TransportClient client) throws IOException {
		IndexResponse response = client.prepareIndex("company", "employee", "1")
			.setSource(XContentFactory.jsonBuilder()
					.startObject()
					.field("name", "jack")
					.field("age", 18)
					.field("position", "technique software")
					.field("country", "china")
					.field("join_date", "2018-01-01")
					.field("salary", 10000)
					.endObject())
			.get();
		
		System.out.println(response);
	}
}
