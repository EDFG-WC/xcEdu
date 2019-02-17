<template>

  <div>
    <el-form :model="pageForm" label-width="80px" :rules="pageRules" ref="pageForm">
      <el-form-item label="所属站点" prop="siteId">
        <el-select v-model="pageForm.siteId" placeholder="请选择站点">
          <el-option v-for="item in siteList"
                     :key="item.siteId"
                     :label="item.siteName"
                     :value="item.siteId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择模板" prop="templateId">
        <el-select v-model="pageForm.templateId" placeholder="请选择">
          <el-option v-for="item in templateList"
                     :key="item.templateId"
                     :label="item.templateName"
                     :value="item.templateId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="页面名称" prop="pageName">
        <el-input v-model="pageForm.pageName" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="别名" prop="pageAlias">
        <el-input v-model="pageForm.pageAlias" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="访问路径" prop="pageWebPath">
        <el-input v-model="pageForm.pageWebPath" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="物理路径" prop="pagePhysicalPath">
        <el-input v-model="pageForm.pagePhysicalPath" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="dataUrl" prop="pageDataUrl">
        <el-input v-model="pageForm.pageDataUrl" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-radio-group v-model="pageForm.pageType">
          <el-radio class="radio" label="0">静态</el-radio>
          <el-radio class="radio" label="1">动态</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker type="datetime" placeholder="创建时间" v-model="pageForm.pageCreateTime"></el-date-picker>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addSubmit">提交</el-button>
      <el-button type="primary" @click="go_back">返回</el-button>
    </div>
  </div>
</template>

<script>
  /*编写静态部分, model和VM部分*/
  import * as cmsApi from '../api/cms'

  export default {
    data() {
      return {
        pageForm: {
          siteId: '',
          templateId: '',
          pageName: '',
          pageAlias: '',
          pageWebPath: '',
          pageParameter: '',
          pagePhysicalPath: '',
          pageType: '',
          pageCreateTime: new Date(),
          pageDataUrl: ''
        },
        siteList: [],
        templateList: [],
        pageRules: {
          siteId: [
            {required: true, message: '请选择站点', trigger: 'blur'}
          ],
          templateId: [
            {required: true, message: '请选择模版', trigger: 'blur'}
          ],
          pageName: [
            {required: true, message: '请输入页面名称', trigger: 'blur'}
          ],
          pageWebPath: [
            {required: true, message: '请输入访问路径', trigger: 'blur'}
          ],
          pagePhysicalPath: [
            {required: true, message: '请输入物理路径', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      addSubmit: function () {
        //验证表单
        this.$refs['pageForm'].validate((valid) => {
          if (valid) {
            //确认提示
            this.$confirm('填好了吗, baby?', '提示', {}).then(() => {
              cmsApi.page_add(this.pageForm).then((res) => {
                console.log(res);
                if (res.success) {
                  //this.$message({message: '提交成功', type: 'success'});
                  this.$message.success('提交成功');
                  //提交成功之后清楚填写的内容:
                  this.$refs['pageForm'].resetFields();
                } else if (res.message) {
                  this.$message.error(res.message)
                } else {
                  this.$message.error('提交失败');
                }
              });
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      go_back: function () {
        //操作路由返回
        this.$router.push({
          path: '/cms/page/list',
          query: {
            page: this.$route.query.page,
            siteId: this.$route.query.siteId
          }
        })
      }
    },
    //钩子方法
    created: function () {
      //初始化站点列表
      this.siteList = [
        {
          siteId: '5a751fab6abb5044e0d19ea1',
          siteName: '门户主站'
        },
        {
          siteId: '102',
          siteName: '测试站'
        }
      ]
      //模板列表
      this.templateList = [
        {
          templateId: '5a962b52b00ffc514038faf7',
          templateName: '首页'
        },
        {
          templateId: '5a962bf8b00ffc514038fafa',
          templateName: '轮播图'
        }
      ]
    },
    mounted() {

    }
  }
</script>

<style scoped>
  /*编写页面样式部分, 不是必须*/
</style>
