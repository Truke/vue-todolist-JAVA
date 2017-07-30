<template>
  <el-row class="content">
    <el-col :xs="{span:20,offset:2}" :sm="{span:8,offset:8}">
      <span>
        欢迎：{{name}}！
        <el-button type="text" @click="logoutToDo">退出</el-button>
        你的待办事项是：
      </span>
      <el-input placeholder="请输入待办事项" v-model="todos" @keyup.enter.native="addTodos"></el-input>
      <el-tabs v-model="activeName">
        <el-tab-pane label="待办事项" name="first">
          <el-col :xs="24">
            <template v-if="!Done">
              <template v-for="(item, index) in list">
                <div class="todo-list" v-if="item.status == false">
                  <span class="item">
                    {{ index + 1 }}. {{ item.content }}
                  </span>
                  <span class="pull-right">
                    <el-button size="small" type="primary" @click="update(index)">完成</el-button>
                    <el-button size="small" :plain="true" type="danger" @click="remove(index)">删除</el-button>
                  </span>
                </div>
              </template> 
            </template>
            <div v-else-if="Done">
              暂无待办事项
            </div>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="已完成事项" name="second">
          <template v-if="count > 0">
            <template v-for="(item, index) in list">
              <div class="todo-list" v-if="item.status == true">
                <span class="item finished">
                  {{ index + 1 }}. {{ item.content }}
                </span>
                <span class="pull-right">
                  <el-button size="small" type="primary" @click="update(index)">还原</el-button>
                </span>
              </div>
            </template> 
          </template>
          <div v-else>
            暂无已完成事项
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</template>

<script>
import jwt from 'jsonwebtoken'
export default {

  data () {
    return {
      name: '',
      todos: '',
      activeName: 'first',
      list:[],
      count: 0,
      id: ''
    };
  },
  created(){
    const userInfo = this.getUserInfo();
    if(userInfo != null){
      this.id = userInfo.id;
      this.name = userInfo.name;
    }else{
      this.id = '';
      this.name = ''
    }
    this.getTodolist();
  },
  computed: {
    Done(){
      let count = 0;
      let length = this.list.length;
      for(let i in this.list){
        this.list[i].status == true ? count += 1 : '';
      }
      this.count = count;
      if(count == length || length == 0){
        return true
      }else{
        return false
      }
    }
  },

  methods: {
    addTodos() {
      if(this.todos == '')
        return
      let obj = {
        status: false,
        content: this.todos,
        id: this.id
      }
      this.$http.post('/test/todolistApi?method=add', obj)
        .then((res) => {
          if(res.data.code === 1000){
            this.$message({
              type: 'success',
              message: '创建成功！'
            })
            this.getTodolist();
          }else{
            this.$message.error('创建失败！')
          }
        }, (err) => {
          this.$message.error('创建失败！')
          console.log(err)
        })
      this.todos = '';
    },
    update(index) {
      let obj = {
        id: this.list[index].id,
        status: !this.list[index].status
      }
      this.$http.post('/test/todolistApi?method=update',obj)
        .then((res) => {
          if(res.data.code === 1000){
            this.$message({
              type: 'success',
              message: '任务状态更新成功！'
            })
            this.getTodolist();
          }else{
            this.$message.error('任务状态更新失败！')
          }
        }, (err) => {
          this.$message.error('任务状态更新失败！')
          console.log(err)
        })
    },
    remove(index) {
      let obj = {
        id: this.list[index].id
      }
      this.$http.post('/test/todolistApi?method=del',obj)
        .then((res) => {
          if(res.data.code === 1000){
            this.$message({
              type: 'success',
              message: '任务删除成功！'
            })
            this.getTodolist();
          }else{
            this.$message.error('任务删除失败！')
          }
        }, (err) => {
          this.$message.error('任务删除失败！')
          console.log(err)
        })
    },
    getUserInfo(){
      const token = sessionStorage.getItem('token');
      if(token !== 'null' && token !== null && token !== 'undefined' && token !== undefined){
        let decode = JSON.parse(jwt.decode(token).sub);
        return decode
      }else {
        return null
      }
    },
    getTodolist(){
      this.$http.get('/test/todolistApi')
        .then((res) => {
          console.log(res)
          if(res.data.code === 1000){
            this.list = res.data.data
          }else{
            this.$message.error('获取列表失败！')
          }
        }, (err) => {
          this.$message.error('获取列表失败！')
          console.log(err)
        })
    },
    logoutToDo(){
      this.$http.get('/test/logoutApi') // 将信息发送给后端
        .then((res) => {
          console.log(res);
          if(res.data.code === 1000){ // 如果成功
            sessionStorage.setItem('token',null); // 用sessionStorage把token存下来
            sessionStorage.removeItem('token');
            this.$message({ // 登录成功，显示提示语
              type: 'success',
              message: '退出成功！'
            });
            this.$router.push('/') // 进入todolist页面，登录成功
          }else{
            this.$message.error(res.data.msg); // 登录失败，显示提示语
          }
        }, (err) => {
            this.$message.error('请求错误！')
        })
    }
  }
};
</script>

<style lang="stylus" scoped>
  .el-input
    margin 20px auto
  .todo-list
    width 100%
    margin-top 8px
    padding-bottom 8px
    border-bottom 1px solid #eee
    overflow hidden
    text-align left
    .item
      font-size 20px
      &.finished
        text-decoration line-through
        color #ddd
  .pull-right
    float right
</style>