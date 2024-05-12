<template>
  <div>
    <span @click="openDialog()"><slot></slot></span>
    <card-edit-dialog ref="cardEditDialog"></card-edit-dialog>
  </div>
</template>

<script>
export default {
  name: "card-apply",
  components: {
    'card-edit-dialog': () => import("@/admin/card/dialog/index.vue"),
  },
  methods: {
    openDialog(){
      this.$refs.cardEditDialog.open();
    },
  }
}
</script>

<style scoped>

</style>
