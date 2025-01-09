<template>
  <el-dialog title="图标裁剪" :visible.sync="show" width="800px" append-to-body :close-on-click-modal="false">
    <el-row>
      <el-col :xs="24" :md="12" :style="{height: '350px'}">
        <vue-cropper
          ref="cropper"
          :img="options.img"
          :info="true"
          :autoCrop="options.autoCrop"
          :autoCropWidth="options.autoCropWidth"
          :autoCropHeight="options.autoCropHeight"
          :fixedBox="options.fixedBox"
          :outputType="options.outputType"
          @realTime="realTime"
        />
      </el-col>
      <el-col :xs="24" :md="12" :style="{height: '350px'}">
        <div class="avatar-upload-preview">
          <img :src="previews.url" :style="previews.img" alt=""/>
        </div>
        <div class="avatar-file-name">
          <el-input v-model="options.filename" placeholder="请输入文件名" clearable v-trim></el-input>
        </div>
      </el-col>
    </el-row>
    <br/>
    <el-row>
      <el-col :lg="2" :sm="3" :xs="3">
        <el-upload action="#" :http-request="requestUpload" :show-file-list="false" :before-upload="beforeUpload">
          <el-button size="small">
            选择
            <i class="el-icon-upload el-icon--right"></i>
          </el-button>
        </el-upload>
      </el-col>
      <el-col :lg="{span: 1, offset: 2}" :sm="2" :xs="2">
        <el-button icon="el-icon-plus" size="small" @click="changeScale(1)"></el-button>
      </el-col>
      <el-col :lg="{span: 1, offset: 1}" :sm="2" :xs="2">
        <el-button icon="el-icon-minus" size="small" @click="changeScale(-1)"></el-button>
      </el-col>
      <el-col :lg="{span: 1, offset: 1}" :sm="2" :xs="2">
        <el-button icon="el-icon-refresh-left" size="small" @click="rotateLeft()"></el-button>
      </el-col>
      <el-col :lg="{span: 1, offset: 1}" :sm="2" :xs="2">
        <el-button icon="el-icon-refresh-right" size="small" @click="rotateRight()"></el-button>
      </el-col>
      <el-col :lg="{span: 2, offset: 6}" :sm="2" :xs="2">
        <el-button type="primary" size="small" :disabled="!(Boolean(options.img) && Boolean(options.filename))"
                   @click="uploadImg()">提 交
        </el-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import {VueCropper} from "vue-cropper";

export default {
  name: "card-icon-cropper",
  components: {VueCropper},
  data() {
    return {
      show: false,
      options: {
        img: '',  //裁剪图片的地址
        autoCrop: true,             // 是否默认生成截图框
        autoCropWidth: 200,         // 默认生成截图框宽度
        autoCropHeight: 200,        // 默认生成截图框高度
        fixedBox: true,             // 固定截图框大小 不允许改变
        outputType: "png",           // 默认生成截图为PNG格式
        filename: ''          // 文件名称
      },
      previews: {},
    }
  },
  methods: {
    open() {
      this.show = true;
      this.options.img = '';
      this.options.filename = '';
    },
    // 覆盖默认的上传行为
    requestUpload() {
    },
    // 向左旋转
    rotateLeft() {
      this.$refs.cropper.rotateLeft();
    },
    // 向右旋转
    rotateRight() {
      this.$refs.cropper.rotateRight();
    },
    // 图片缩放
    changeScale(num) {
      num = num || 1;
      this.$refs.cropper.changeScale(num);
    },
    // 上传预处理
    beforeUpload(file) {
      if (file.type.indexOf("image/") === -1) {
        this.$modal.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
      } else {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.options.img = reader.result;
          this.options.filename = file.name.substring(0, file.name.lastIndexOf('.'));
        };
      }
    },
    // 上传图片
    uploadImg() {
      if (!this.options.filename) {
        this.$modal.msgError("请填写文件名");
        return;
      }
      this.$refs.cropper.getCropBlob(data => {
        let formData = new FormData();
        formData.append("file", data, this.options.filename + '.png');
        this.$http.post('/api/v1/upload/default', formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(res => {
          this.$modal.msgSuccess("保存成功");
          this.show = false;
          this.$emit('uploaded');
        });
      });
    },
    // 实时预览
    realTime(data) {
      this.previews = data;
    },
  }
}
</script>

<style scoped>
.avatar-upload-preview {
  top: 40%
}

.avatar-file-name {
  position: relative;
  top: 20%;
  left: 25%;
  width: 50%;
}
</style>
