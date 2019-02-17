<template>
  <div>
    <el-button type="primary" size="small" v-on:click="query">查询</el-button>
    <el-table
      :data="tableData3"
      height="250"
      border
      style="width: 100%">
      <el-table-column
        prop="date"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="gender"
        label="性别">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>
    <el-pagination
      layout="prev, pager, next"
      :page-size="10"
      :total="total"
      :current-page="params.page"
      @current-change="changePage"
      style="float:right">
    </el-pagination>
  </div>

</template>

<script>
  export default {
    name: "fuck_thing",
    data() {
      return {
        list: [],
        total: 50,
        params: {
          page: 2,
          size: 10
        },
        tableData3: [{
          date: '2018-12-25',
          name: '阿光',
          gender: '男',
          address: '四川大学'
        }, {
          date: '2018-12-25',
          name: '阿光',
          gender: '男',
          address: '四川大学'
        }, {
          date: '2018-12-25',
          name: '阿光',
          gender: '男',
          address: '四川大学'
        }, {
          date: '2018-12-25',
          name: '阿光',
          gender: '男',
          address: '四川大学'
        }, {
          date: '2018-12-25',
          name: '阿光',
          gender: '男',
          address: '四川大学'
        }, {
          date: '2018-12-25',
          name: '阿光',
          gender: '男',
          address: '四川大学'
        }, {
          date: '2018-12-25',
          name: '阿光',
          gender: '男',
          address: '四川大学'
        }]
      }
    },
    methods: {
      query: function () {
        alert("美滴很")
      },
      changePage: function () {
        this.query()
      }
    }
  }
</script>

<style scoped>

</style>
