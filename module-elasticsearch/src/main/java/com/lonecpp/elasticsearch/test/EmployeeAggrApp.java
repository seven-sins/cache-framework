package com.lonecpp.elasticsearch.test;

/**
 * @author seven sins
 * @date 2018年1月25日 下午9:30:18
 */
public class EmployeeAggrApp {
	
//	@SuppressWarnings("resource")
//	public static void main(String[] args) throws IOException {
//		Settings settings = Settings.builder().put("cluster.name", "my-application").build();
//		
//		TransportClient client = new PreBuiltTransportClient(settings)
//		        .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//		
//		SearchResponse searchResponse = client.prepareSearch("company")
//				.addAggregation(AggregationBuilders.terms("group_by_country")
//						.field("country")
//						.subAggregation(AggregationBuilders
//								.dateHistogram("group_by_join_date")
//								.field("join_date")
//								.dateHistogramInterval(DateHistogramInterval.YEAR)
//								.subAggregation(AggregationBuilders.avg("avg_salary").field("salary")))
//						).execute().actionGet();
//		
//		List<Aggregation> aggrList = searchResponse.getAggregations().asList();
//		for(Aggregation item: aggrList) {
//			
//		}
//		System.out.println(searchResponse.getAggregations().asList());
//		
//		client.close();
//	}



}
