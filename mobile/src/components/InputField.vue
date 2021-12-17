<template>
  <div class="row q-mb-none">
    <q-input
      standout="bg-primary text-white"
      class="col"
      ref="model"
      :modelValue="model"
      @update:modelValue="event => $emit('update:' + model, event)"
    >
      <template v-slot:append>
        <q-icon
        name="close"
        class="cursor-pointer"
        />
      </template>
    </q-input>
  </div>
</template>

<script>
export default {
  props: {
    label: {
      type: String,
      default: ''
    },
    model: {
      type: String,
      default: ''
    }
  }
}
</script>
