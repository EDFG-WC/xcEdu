package com.xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: wangc
 * @Date: 2019/1/20 20:20
 * @Description: GridFs的测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFSTest {

  @Autowired
  private GridFsTemplate gridFsTemplate;

  @Autowired
  private GridFSBucket gridFSBucket;

  @Test
  public void testGridFs() throws FileNotFoundException {
    //指定存储的文件
    File file = new File("C:/Users/eniac/Documents/index_banner.ftl");
    //定义输入流
    FileInputStream fis = new FileInputStream(file);
    //向GridFs存储文件
    ObjectId objectId = gridFsTemplate.store(fis, "轮播图测试文件002");
    //得到文件Id
    String fileId = objectId.toString();
    System.out.println(fileId);
  }

  @Test
  public void testGridFsRead() throws IOException {
    String id1 = "5a795bbcdd573c04508f3a59";
    String id2 = "5b091f97c5e9b7070c94a2bb";
    String id3 = "5a7719d76abb5042987eec3a";
    String id4 = "index.html";
    String id5 = "5c45b65b183fef079c5b5c51";
    //根据id查询文件
    GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id2)));
    //打开下载流对象
    ObjectId objectId = gridFSFile.getObjectId();
    GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(objectId);
    //创建gridFsReource, 用于获取流对象
    GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
    //获取流中的数据
    String s = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
    System.out.println(s);
  }

  @Test
  public void testDelFile() throws IOException {
    //根据文件id删除fs.files和fs.chunks中的记录
    gridFsTemplate.delete(Query.query(Criteria.where("_id").is("5b32480ed3a022164c4d2f92")));
  }
}
