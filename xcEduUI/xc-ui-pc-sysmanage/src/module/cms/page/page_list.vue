<template>

  <div>
    <!--编写静态部分, 即view部分, template不能为空-->
    <el-form :model="params">
      页面站点:
      <el-select v-model="params.siteId" clearable placeholder="请选择站点">
        <el-option
          v-for="item in siteList"
          :key="item.siteId"
          :label="item.siteName"
          :value="item.siteId">
        </el-option>
      </el-select>
      页面别名:
      <el-input v-model="params.pageAlias" style="width: 100px"></el-input>
      页面名称:
      <el-input v-model="params.pageName" style="width: 100px"></el-input>
      页面类型:
      <el-select v-model="params.pageType" clearable placeholder="请选择页面类型">
        <el-option
          v-for="item in pageTypeList"
          :key="item.pageType"
          :label="item.pageTypeDisplay"
          :value="item.pageType">
        </el-option>
      </el-select>
      <el-button type="primary" size="small" v-on:click="query">查询</el-button>
      <router-link class="mui-tab-item"
                   :to="{path:'/cms/page/add',query:{
                   page:this.params.page,
                   siteId:this.params.siteId}}">
        <el-button type="primary" size="small">添加页面</el-button>
      </router-link>
    </el-form>
    <el-table
      :data="list"
      border
      style="width: 100%">
      <el-table-column
        type="index"
        width="60">
      </el-table-column>
      <el-table-column
        prop="pageName"
        label="页面名称"
        width="120">
      </el-table-column>
      <el-table-column
        prop="pageAlias"
        label="别名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="pageType"
        label="页面类型"
        width="110">
      </el-table-column>
      <el-table-column
        prop="pageWebPath"
        label="访问路径"
        width="250">
      </el-table-column>
      <el-table-column
        prop="pagePhysicalPath"
        label="物理路径"
        width="250">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
      <el-table-column
        prop="pageCreateTime"
        label="创建时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="dataUrl"
        label="数据链接"
        width="180">
      </el-table-column>
      <el-table-column label="操作" width="120" align="center">
        //这是一个插槽, 这个槽的名字叫pageCao
        <template slot-scope="pageCao">
          <el-button
            size="small"
            type="text"
            @click="editCms(pageCao.row.pageId)">编辑
          </el-button>
          <el-button
            size="small"
            type="text"
            @click="delCms(pageCao.row.pageId)">删除
          </el-button>
          <el-button
            size="small"
            type="text"
            @click="previewCms(pageCao.row.pageId)">预览
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      layout="prev, pager, next"
      :page-size="this.params.size"
      :total="total"
      :current-page="this.params.page"
      @current-change="changePage"
      style="float:right">
    </el-pagination>
  </div>
</template>

<script>
  /*编写静态部分, model和VM部分*/
  import * as cmsApi from '../api/cms'
  import ElSelectDropdown from "element-ui/packages/select/src/select-dropdown";

  export default {
    components: {ElSelectDropdown},
    data() {
      return {
        //站点列表
        siteList: [],
        list: [],
        total: 50,
        params: {
          page: 1,
          size: 10,
          pageAlias: '',
          siteId: '',
          pageName: '',
          pageType: ''
        }
      }
    },
    methods: {
      query: function () {
        //alert("吃葡萄不吐葡萄皮")
        //打印页面类型
        //alert(this.params.pageType)
        //调用服务端的接口
        cmsApi.page_list(this.params.page, this.params.size, this.params).then((result) => {
          //把结果赋给本地变量
          console.log(result)
          this.total = result.queryResult.total
          this.list = result.queryResult.list
        })
      },
      changePage: function (page) {
        //打印形参
        //alert(page)
        //接收page参数
        this.params.page = page
        //调用query方法:
        this.query()
      },
      getSiteList: function () {
        cmsApi.page_getSiteList().then((res) => {
          //alert(res.queryResult.list)
          this.siteList = res.queryResult.list
        })
      },
      editCms: function (pageId) {
        this.$router.push({
          path: '/cms/page/edit/' + pageId, query: {page: this.params.page, siteId: this.params.siteId}
        })
      },
      delCms: function (pageId) {
        this.$confirm('确认删除吗?', '提示', {}).then(() => {
          cmsApi.page_del(pageId).then((res) => {
            if (res.success) {
              this.$message.success('删除成功!');
              this.query()
            } else {
              this.$message.error('删除失败!')
            }
          });
        });
      },
      previewCms: function (pageId) {
        window.open("http://www.xuecheng.com/cms/preview/"+pageId)
      }
    },
    //钩子方法
    created() {
      this.params.page = Number.parseInt(this.$route.query.page || 1);
      this.params.siteId = this.$route.query.siteId || '';
    },
    mounted() {
      //当dom元素渲染完毕调用query方法
      this.query()
      //初始化站点列表
      /* this.siteList = [
         {
           siteId: '119278156ea1',
           siteName: '门户主站'
         },
         {
           siteId: '102',
           siteName: '测试站'
         }
       ]*/
      this.getSiteList()
      this.pageTypeList = [
        {
          pageType: 0,
          pageTypeDisplay: '静态'
        },
        {
          pageType: 1,
          pageTypeDisplay: '动态'
        }
      ]
    }
  }
</script>

<style scoped>
  /*编写页面样式部分, 不是必须*/
</style>
