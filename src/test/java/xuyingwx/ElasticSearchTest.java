package xuyingwx;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse.AnalyzeToken;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequestBuilder;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElasticSearchTest {
	private static Logger logger = Logger.getLogger(ElasticSearchTest.class.getName());
     public final static String HOST = "127.0.0.1";
     
     public final static int PORT = 9300;//http请求的端口是9200，客户端是9300
     private static TransportClient client ;
     @Before
     public static void begin() throws UnknownHostException {
         //创建客户端
         client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));
         System.out.println("开启");
     }
     @After
     public static void end() throws Exception {
    	 if(client!=null){
    		 client.close();
    		 System.out.println("关闭");
    	 }
     }
     
     @Test
     public void getData1() {
         GetResponse getResponse = client.prepareGet("peple", "student", "1").get();
         logger.info("索引库的数据:" + getResponse.getSourceAsString());
     }
     /**
      * 创建索引
      * @Description: TODO
      * @author xuying.chen 
      * @date 2018年4月13日 下午9:14:34 
      * @param indices
      */
     public static void createIndex(String index,String mappingType)throws Exception{
    	 client.admin().indices().prepareCreate(index).execute().actionGet(); 
         XContentBuilder builder=XContentFactory.jsonBuilder()
                 .startObject()
                 .startObject(index)
                 .startObject("properties")
                 .startObject("id").field("type", "integer").field("store", "yes").endObject()
                 .startObject("content").field("type", "text").field("store", "yes").field("analyzer","ik").field("index","analyzed").endObject()
                 .startObject("createTime").field("type", "date").field("store", "yes").field("analyzer","ik").field("index","analyzed").endObject()
                 .endObject()
                 .endObject()
                 .endObject();
         PutMappingRequest mapping = Requests.putMappingRequest(index).type(mappingType).source(builder);
         client.admin().indices().putMapping(mapping).actionGet();
     }

     public static void deleteCluterName(String indices){
    	//根据索引名称删除索引库文档(或者client.admin().indices().prepareDelete("class_index").execute().actionGet();)
//		 IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indices);
//		 client.admin().indices().exists(inExistsRequest).actionGet();
    	 client.admin().indices().prepareDelete(indices).execute().actionGet();
     }
     
     public static void main(String[] args) throws Exception {
    	 begin();
    	 //
    	 if(client!=null){
//    		 XContentBuilder mapping = XContentFactory.jsonBuilder()
//    				 .startObject()
//    				.startObject("student_index1")
//    				 	.startObject("properties")
//	    		 			.startObject("field1").field("analyzer","ik").field("search_analyzer","ik_smart").endObject()
//	    		 		.endObject()
//	    		 		.endObject()
//	    		 	.endObject();  
//    		 IndexResponse executeResponse = client.prepareIndex("student_index1", "student_type1")
//						.setSource(mapping)
//						.get();
    		 
    		 
//    		 deleteCluterName("lianan");
//    		 createIndex("aaa", "aaa");
//    		 //自带field方法
    		 //创建一个索引库为：msg消息队列,类型为：tweet,id为1(只是es里面的唯一标识,跟实体类的id不一定一样,可以指定一样)
//    		 IndexResponse executeResponse = client.prepareIndex("student_index", "student_type", "8")
//    				 						.setSource(
//    				 								XContentFactory.jsonBuilder()
//					 									.startObject()
//					 									.field("name", "zhangsan")
//					 									.field("age", "23")
//					 									.field("from", "china")
//					 									.endObject()
//				 									)
//				 							.get();
//    		 logger.info("索引名称:" + executeResponse.getIndex() + "\n类型:" + executeResponse.getType() + "\n文档ID:" + executeResponse.getId() + "\n当前实例状态:" + executeResponse.status());
    		 
    		 //插入json字符串
//    		 User u = new User("9","wangwu","12","china",1,2.4,new Date());
//    		 IndexResponse executeResponse = client.prepareIndex("student_index", "student_type", u.getId())
//    				 						.setSource(JSONObject.toJSONString(u),
//    				 								XContentType.JSON)
//				 							.get();
//    		 logger.info("索引名称:" + executeResponse.getIndex() + "\n类型:" + executeResponse.getType() + "\n文档ID:" + executeResponse.getId() + "\n当前实例状态:" + executeResponse.status());
    		 
//    		 //批量插入
//    		 User u = new User("5","wangwu5","125","china5",1,2.4,new Date());
//    		 User u1 = new User("6","wangwu6","126","china6",1,2.4,new Date());
//    		 BulkRequestBuilder bulkRequest = client.prepareBulk();  
//    		 bulkRequest.add(client.prepareIndex("student_index", "student_type", u.getId())  
//    				 		.setSource(JSONObject.toJSONString(u),XContentType.JSON)
//    			             );  
//    		 bulkRequest.add(client.prepareIndex("student_index", "student_type", u1.getId())  
//    				 .setSource(JSONObject.toJSONString(u1),XContentType.JSON)
//    				 );  
//    		 bulkRequest.get();
    		 
		    //插入map
//		 	Map<String, Object> map = new HashMap<String,Object>();
//		 	map.put("id", "13");
//		 	map.put("name", "中国再亚洲,美国");
//		 	map.put("age",	"44");
//		 	map.put("from", "eng");
//		 	IndexResponse executeResponse = client.prepareIndex("student_index", "student_type", (String) map.get("id")).setSource(map).get();
    		 
    		 
//    		 //根据内置id查询,找出来并修改实体类的id,再放进去
//    		 GetResponse getResponse = client.prepareGet("student_index", "student_type", "7").get();
//    		 User user = JSON.parseObject(getResponse.getSourceAsString(), User.class);  
//    		 user.setId("7");
//    		 IndexResponse executeResponse = client.prepareIndex("student_index", "student_type", user.getId())
//						.setSource(JSONObject.toJSONString(user),
//								XContentType.JSON)
//						.get();
    		 
    		 
    		 //根据id删除索引库中某文档
//    		 DeleteResponse deleteResponse = client.prepareDelete("peple", "student", "1").get();
//    		 logger.info("deleteResponse索引名称:" + deleteResponse.getIndex() + "\n deleteResponse类型:" + deleteResponse.getType() + "\n deleteResponse文档ID:" + deleteResponse.getId() + "\n当前实例deleteResponse状态:" + deleteResponse.status());
    		 //根据查询条件删除文档
//    		 BulkIndexByScrollResponse response =
//    				    DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
//    				        .filter(QueryBuilders.matchQuery("gender", "male")) 
//    				        .source("persons")                                  
//    				        .get();                                             
//    				long deleted = response.getDeleted(); 
    		 
//    		 String response  = client.prepareSearch("student_index").setSize(0).execute().actionGet().toString();
//    		 JSONObject JsonResponse = JSONObject.parseObject(response);
//    		 JSONObject JsonResponse_hits = (JSONObject) JsonResponse.get("hits") ;//(JsonResponse.get("hits"))
//    		 String count_num = JsonResponse_hits.get("total").toString();
//    		 Integer size = Integer.valueOf(count_num);
//    		 System.out.println(size);
//    		 //查询索引库数据
//    		 for (int i = 1; i <=size; i++) {
//    			 GetResponse getResponse = client.prepareGet("student_index", "student_type", i+"").get();
//    			 System.out.println(getResponse.getSourceAsString());
//    			 User user = JSON.parseObject(getResponse.getSourceAsString(), User.class);  
//    			 //logger.info("索引库的数据:" + user);
//    		 }
    		 
//    		 SearchResponse  getResponse = client.prepareSearch("student_index").setTypes("student_type")
//    			        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//    				 	.setFrom(0).setSize(6)
//    				 	.setExplain(true)
//    			        .get();
    		 
//             QueryBuilder qb = QueryBuilders.fuzzyQuery("name", "wangwu");
//             SearchResponse getResponse = client.prepareSearch("student_index").setTypes("student_type").setQuery(qb).execute().get();
//             getSC(getResponse);
    	
    		 
    		 
////    		MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "大师").analyzer("ik_max_word");
//    		QueryStringQueryBuilder matchQuery = QueryBuilders.queryStringQuery("潮州").field("field_2");
//    	
//		 	HighlightBuilder hiBuilder=new HighlightBuilder();
//			hiBuilder.preTags("<h2>");
//			hiBuilder.postTags("</h2>");
//			hiBuilder.field("field_2");
//			SearchResponse actionGet = client.prepareSearch("index_name_test")
//				.setTypes("type_name_test")
//				.setQuery(matchQuery)
//				.highlighter(hiBuilder)
//				.execute()
//				.actionGet();
//			SearchHits hits = actionGet.getHits();
//			for (SearchHit searchHit : hits) {
//				Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
//				HighlightField highlightField = highlightFields.get("field_2");
//				Text[] fragments = highlightField.getFragments();
//				System.out.println(fragments[0].toString());
//				System.out.println(searchHit.getSourceAsString());
//			}
    		 
//    		 //获取所有数据
//    		 //创建请求request
//    		 SearchRequestBuilder searchRequest = client.prepareSearch("index_name_test")
//				.setTypes("type_name_test")
//				.setSize(1024)//这里设置得数据是想要查询的数据量,不能大于10000,所以想要更多,需要使用scroll
//				.setScroll(new TimeValue(60000));//设置上下文时间,就是ScrollId的有效时间
//    		 //执行请求
//    		 SearchResponse searchResponse = searchRequest.get();
//    		 do{
//    			 //搜索结果
//    			 SearchHits hits = searchResponse.getHits();
//    			 SearchHit[] hits2 = hits.getHits();
//    			 for (SearchHit searchHit : hits2) {
//    				 Map<String, Object> source = searchHit.getSource();
//				 	 System.out.println(searchHit.getSourceAsString());
//				 }
//    			 //创建下一个请求
//    			 SearchScrollRequestBuilder setScroll = client.prepareSearchScroll(searchResponse.getScrollId())
//			 				.setScroll(new TimeValue(60000));
//    			//执行请求
//    			 searchResponse = setScroll.get();//get()等于execute().actionGet();
//    		 }while(searchResponse.getHits().getHits().length != 0);
    		 
    		 //获取字段信息
//    		 ImmutableOpenMap<String, MappingMetaData> mappings = client.admin().cluster().prepareState().execute()
//             .actionGet().getState().getMetaData().getIndices().get("index_name_test").getMappings();
//    		 String mapping = mappings.get("type_name_test").source().toString();
//    		 System.out.println(mapping);
//    		 System.out.println();
    		 
    	 }
    	 end();
	}
}
