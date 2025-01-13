<template>
  <div class="floating-tools">
    <!-- 联系客服 -->
    <el-tooltip content="联系客服" placement="left">
    <el-popover
      placement="left"
      trigger="click"
      v-model:visible="showQrCode1"
    >
      <template #reference>
        <el-button
          type="primary"
          icon="el-icon-service"
          circle
          class="tool-button"
        />
      </template>
      <div class="qr-code-container">
        <img src="./assets/wechat-qrcode.jpg" alt="客服微信二维码" class="qr-code" />
        <p>扫描二维码联系客服</p>
      </div>
    </el-popover>
    </el-tooltip>
    <!-- 商务合作 -->
    <el-tooltip content="商务合作" placement="left">
    <el-popover
      placement="left"
      trigger="click"
      v-model:visible="showQrCode2"
    >
      <template #reference>
        <el-button
          type="success"
          icon="el-icon-suitcase"
          circle
          class="tool-button"
        />
      </template>
      <div class="qr-code-container">
        <img src="./assets/wechat-qrcode.jpg" alt="商务合作微信二维码" class="qr-code" />
        <p>扫描二维码联系商务合作</p>
      </div>
    </el-popover>
    </el-tooltip>
    <!-- 公众号 -->
    <el-tooltip content="公众号" placement="left">

    <el-popover
      placement="left"
      trigger="click"
      v-model:visible="showQrCode3"
    >
      <template #reference>
        <el-button
          type="warning"
          icon="el-icon-chat-dot-round"
          circle
          class="tool-button"
        />
      </template>
      <div class="qr-code-container">
        <img src="./assets/wechat-qrcode.jpg" alt="微信公众号二维码" class="qr-code" />
        <p>扫描二维码关注公众号</p>
      </div>
    </el-popover>
    </el-tooltip>
    <!-- 反馈建议 -->
    <el-tooltip content="反馈建议" placement="left">
    <el-popover
      placement="left"
      trigger="click"
      v-model:visible="showQrCode4"
    >
      <template #reference>
        <el-button
          type="danger"
          icon="el-icon-edit-outline"
          circle
          class="tool-button"
        />
      </template>
      <div class="qr-code-container">
        <img src="./assets/wechat-qrcode.jpg" alt="反馈建议微信二维码" class="qr-code" />
        <p>扫描二维码反馈建议</p>
      </div>
    </el-popover>
    </el-tooltip>
    <!-- 回到顶部 -->
    <el-button
      type="info"
      icon="el-icon-top"
      circle
      class="tool-button"
      @click="scrollToTop"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      showQrCode1: false, // 控制客服二维码显示
      showQrCode2: false, // 控制商务合作二维码显示
      showQrCode3: false, // 控制公众号二维码显示
      showQrCode4: false, // 控制反馈建议二维码显示
    };
  },
  methods: {
    scrollToTop() {
      const appMain = document.querySelector('.app-main');
      if (appMain) {
        appMain.scrollTo({
          top: 0,
          behavior: 'smooth',
        });
      } else {
        console.warn('未找到 .app-main 元素');
      }
    },
  },
};
</script>

<style scoped>
.floating-tools {
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-end;
}

.tool-button {
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.qr-code-container {
  text-align: center;
  padding: 16px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.qr-code {
  width: 120px;
  height: 120px;
  display: block;
  margin-bottom: 10px;
}

.qr-code-container p {
  margin: 0;
  font-size: 14px;
  color: #666;
}
</style>
