<template>
  <div style="padding: 10px; width: 100%">
    <div>
      <el-statistic
          group-separator=","
          :precision="2"
          :value="dataList.viewers"
          :title="title1"
      >
      </el-statistic>
    </div>
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" size="small" @click="add">新增</el-button>
    </div>

    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input
        v-model="search"
        placeholder="请输入关键字"
        style="width: 20%"
        clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load"
        >查询</el-button
      >

    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" width="50px" sortable>
      </el-table-column>
      <el-table-column prop="title" label="文章标题"> </el-table-column>
      <el-table-column label="文章图片" width="180">
        <template slot-scope="scope">
          <div class="image-container">
            <img v-if="scope.row.picture" :src="scope.row.picture" alt="文章图片" class="article-image">
            <img v-if="scope.row.cover" :src="scope.row.cover" alt="封面图片" class="cover-image">
          </div>
        </template>

      </el-table-column>

      <el-table-column prop="type" label="文章类型" :formatter="formatType">
      </el-table-column>

      <el-table-column prop="content" label="文章内容" width="500px">

        <template slot-scope="scope" >
          <div style="white-space: pre-wrap;width: 100%;word-break: break-all">
            {{scope.row.content}}

          </div>
        </template>
      </el-table-column>
      <el-table-column label="文章创建时间">
        <template v-slot="{ row }">
          {{ formatDateTime(row.createTime) }}
        </template>
      </el-table-column>
      <el-table-column label="文章更新时间">
        <template v-slot="{ row }">
          {{ formatDateTime(row.updateTime) }}
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" size="mini">编辑</el-button>
          <el-popconfirm
            title="确定删除吗？"
            @confirm="handleDelete(scope.row.id)">
            <el-button
              type="danger"
              slot="reference"
              size="mini"
              style="margin-left: 9px"
              >删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

      <el-dialog title="表单" :visible.sync="dialogVisible" width="80%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="文章标题">
            <el-input v-model="form.title" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="文章内容">
            <el-input
              v-model="form.content"
              type="textarea"
              :rows="5"
              style="width: 80%; height: 200px"></el-input>
          </el-form-item>
          <el-form-item label="上传图片" required>
            <el-upload
                ref="uploadNumRef"
                :file-list="tableData"
                list-type="picture-card"
                action="#"
                :show-file-list="false"
                :http-request="uploadCover3"
                :before-upload="beforeUploadImage">
              <div v-if="!form.picture" class="upload-area">
                <i class="el-icon-upload upload-icon"></i>
                <div class="upload-text">点击上传图片</div>
              </div>
              <img
                  v-else
                  :src="form.picture"
                  class="uploaded-image"
                  alt="文章图片" />
            </el-upload>
          </el-form-item>
          <el-form-item label="上传封面图片" required>
            <el-upload
                ref="uploadNumRef"
                :file-list="tableData"
                list-type="picture-card"
                action="#"
                :show-file-list="false"
                :http-request="uploadCover4"
                :before-upload="beforeUploadImage">
              <div v-if="!form.cover" class="upload-area">
                <i class="el-icon-upload upload-icon"></i>
                <div class="upload-text">点击上传封面</div>
              </div>
              <img
                  v-else
                  :src="form.cover"
                  class="uploaded-image"
                  alt="封面图片" />
            </el-upload>
          </el-form-item>

          <el-form-item label="文章类别" v-model="radio">
            <el-radio v-model="form.type" :label="1">广西民族大学</el-radio>
            <el-radio v-model="form.type" :label="2">闽江师范高等专科学校</el-radio>
            <el-radio v-model="form.type" :label="3">福州市艺术学校</el-radio>
            <el-radio v-model="form.type" :label="4">中山大学</el-radio>
            <el-radio v-model="form.type" :label="5">香港中文大学</el-radio>
            <el-radio v-model="form.type" :label="6">印尼丹戎布拉大学孔院</el-radio>
            <el-radio v-model="form.type" :label="7">印尼阿拉扎大学</el-radio>
            <el-radio v-model="form.type" :label="8">泰国马哈沙拉坎大学</el-radio>
            <el-radio v-model="form.type" :label="9">老挝国立大学</el-radio>
            <el-radio v-model="form.type" :label="10">土耳中资民企商会</el-radio>
            <el-radio v-model="form.type" :label="11">印尼中资民企商会</el-radio>
            <el-radio v-model="form.type" :label="12">厄瓜多尔中华桥乡中心</el-radio>
            <el-radio v-model="form.type" :label="13">广西'桂姐姐'心理教育</el-radio>
            <el-radio v-model="form.type" :label="14">'茉莉姐姐'亲子教育工作室</el-radio>
            <el-radio v-model="form.type" :label="15">到海峡的另一边去</el-radio>
            <el-radio v-model="form.type" :label="16">顶梁柱母亲心理服务</el-radio>
            <el-radio v-model="form.type" :label="17">中学心理筛查</el-radio>
            <el-radio v-model="form.type" :label="18">'青苗计划'社区家长课堂</el-radio>
            <el-radio v-model="form.type" :label="19">守护星星畅享才艺</el-radio>
            <el-radio v-model="form.type" :label="20">成长助学</el-radio>
            <el-radio v-model="form.type" :label="21">义工风采</el-radio>
          </el-form-item>
          <el-form-item label="创建时间">
            <el-date-picker
              v-model="form.createTime"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              style="width: 80%"
              @change="handleDateTimeChange"></el-date-picker>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
//留一下，少东西了（不是很重要但是我查一下）

export default {
  name: "HomeView",
  data() {
    return {
      form: {
        id: "",
        title: "",
        content: "",
        type: "",
        createTime: "",
        updateTime: "",
        picture: "",
        cover: "",
      },
      dialogVisible: false,
      currentPage: 1,
      total: 0,
      search: "",
      pageSize: 10,
      pageNum: 1,
      tableData: [],
      radio: 1,
      like: true,
      viewers: 0,
      title1: "访问量",
      dataList : [],
    };
  },
  created() {
    this.load();
    this.load2();
  },
  methods: {
    load() {
      request.getRequest("admin/essay", {
        pageNum: this.currentPage,
        pageSize: this.pageSize,
        search: this.search
        })
        .then((res) => {
          this.tableData = res.data.data.records;
          this.total = res.data.data.total;
        });
    },
    load2(){
      request.getRequest("admin/essay/viewer").then(res => {
        this.dataList = res.data.data;
      })
    },
    uploadCover3({ file }) {
      this.isLoading = true;
      let param = {
        file: file,
      };
      request.uploadFileRequest("/api/upload/volunteer", param).then((res) => {
        this.isLoading = false;
        this.form.picture = res.data.data;
      });
    },
    uploadCover4({ file }) {
      this.isLoading = true;
      let param = {
        file: file,
      };
      request.uploadFileRequest("/api/upload/volunteer", param).then((res) => {
        this.isLoading = false;
        this.form.cover = res.data.data;
      });
    },
    // 有一个关于request的错误, 待解决
    beforeUploadImage(file) {
      let type = file.type;
      const isJPG = type.startsWith("image");
      const isLt10M = file.size / 1024 / 1024 < 10;

      if (!isJPG) {
        this.$message.error("只能上传图片!");
        return false;
      }
      if (!isLt10M) {
        this.$message.error("图片大小不能超过 10MB!");
        return false;
      }
      return true;
    },
    // handleExceed(files, fileList) {
    //   this.$message.error(`最多只能上传2张图片！`);
    // },
    // 格式化文章的类型
    formatType(row, column, cellValue) {
      const typeMap = {
        1: "广西民族大学",
        2: "闽江师范高等专科学校",
        3: "福州市艺术学校",
        4: "中山大学",
        5: "香港中文大学",
        6: "印尼丹戎布拉大学孔院",
        7: "印尼阿拉扎大学",
        8: "泰国马哈沙拉坎大学",
        9: "老挝国立大学",
        10: "土耳中资民企商会",
        11: "印尼中资民企商会",
        12: "厄瓜多尔中华桥乡中心",
        13: "广西'桂姐姐'心理教育",
        14: "'茉莉姐姐'亲子教育工作室",
        15: "到海峡的另一边去",
        16: "顶梁柱母亲心理服务",
        17: "中学心理筛查",
        18: "'青苗计划'社区家长课堂",
        19: "守护星星畅享才艺",
        20: "成长助学",
        21: "义工风采",
      };
      return typeMap[cellValue] || cellValue;
    },
    // 格式化日期时间
    formatDateTime(dateTimeString) {
      const date = new Date(dateTimeString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      const seconds = String(date.getSeconds()).padStart(2, "0");
      const milliseconds = String(date.getMilliseconds()).padStart(3, "0");
      // 生成ISO 8601格式的日期时间字符串，并添加'Z'来表示UTC时区
      return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}.${milliseconds}Z`;
    },
    handleDateTimeChange(value) {
      this.form.createTime = this.formatDateTime(value);
    },
    add() {
      this.dialogVisible = true;
      this.form = {};
    },
    handleDelete(id) {
      request.deleteRequest("admin/essay/del/" + id).then((res) => {
        if (res) {
          this.$message.success("删除成功");
          this.load();
        } else {
          this.$message.error("删除失败");
        }
      });
    },

    save() {
      console.log(this.form.id);
      if (this.form.id) {
        request.postRequest("admin/essay/update", this.form).then((res) => {
          this.load();
          if (res) {
            this.$message.success("更新成功");
            this.dialogVisible = false;
          } else {
            this.$message.error("更新失败");
          }
        });
      } else {
        request.postRequest("admin/essay/save", this.form).then((res) => {
          this.load();
          if (res) {
            this.$message.success("添加成功");
            this.load();
            this.dialogVisible = false;
          } else {
            this.$message.error("添加失败");
            this.dialogVisible = false;
          }
        });
      }
    },
    handleCurrentChange(newPage) {
      // 更新 currentPage 的值
      this.currentPage = newPage;
      // 重新加载数据
      this.load();
    },
    handleEdit(row) {
      this.radio = +row.type;
      this.dialogVisible = true;
      this.form = row;
    },
    handleSizeChange(pageSize) {
      //改变当前每页的个数触发
      this.pageSize = pageSize;
      this.load();
    },

  },
};
</script>
<style scoped>
.image-container {
  display: flex;
  align-items: center; /* 垂直居中 */
  justify-content: space-around; /* 平均分布图片 */
}

.article-image,
.cover-image {
  width: 50px;
  height: 50px;
  border-radius: 4px; /* 圆角边框 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  margin-right: 10px; /* 图片之间的间隔 */
}

/* 如果你想让图片鼠标悬停时有一个效果，可以添加以下样式 */
.article-image:hover,
.cover-image:hover {
  transform: scale(1.05); /* 略微放大图片 */
  transition: transform 0.3s ease; /* 平滑的过渡效果 */
}

.upload-area {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  border: 2px dashed #d9d9d9; /* 加宽虚线边框 */
  border-radius: 8px; /* 稍微调整圆角 */
  background-color: #f5f7fa; /* 轻微调整背景颜色 */
  cursor: pointer;
  transition: border-color 0.3s; /* 边框颜色过渡效果 */
}

.upload-area:hover {
  border-color: #409eff; /* 悬停时边框颜色 */
}

.upload-icon {
  font-size: 32px; /* 增大图标大小 */
  color: #909399; /* 调整图标颜色 */
}

.upload-text {
  margin-top: 10px; /* 增加文字与图标间距 */
  font-size: 16px; /* 增大文字大小 */
  color: #606266; /* 文字颜色 */
}

.uploaded-image {
  width: 100%;
  height: 100%;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.16), 0 2px 4px rgba(0, 0, 0, 0.12);
}
</style>
