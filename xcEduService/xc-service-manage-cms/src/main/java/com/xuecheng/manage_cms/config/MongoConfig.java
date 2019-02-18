package com.xuecheng.manage_cms.config;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

/**
 * @Auther: wangc
 * @Date: 2019/1/20 20:50
 * @Description: 启动时可以作为一个bean被扫描
 */
@Configuration
public class MongoConfig {

	/*@Value("${spring.data.mongodb.authentication-database}")
	String db;

	@Bean
	public GridFSBucket getGridFSBucket(MongoClient mongoClient) {
		MongoCredential mongoCredential = MongoCredential.createCredential("root","xc_cms","123".toCharArray());
		ServerAddress serverAddress = new ServerAddress("localhost", 27017);
		mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential));
		MongoDatabase database = mongoClient.getDatabase(db);
		GridFSBucket bucket = GridFSBuckets.create(database);
		return bucket;
	}*/

  @Autowired
  private MongoDbFactory mongoDbFactory;

  @Bean
  public GridFSBucket getGridFSBuckets() {
    MongoDatabase db = mongoDbFactory.getDb();
    return GridFSBuckets.create(db);
  }
}
