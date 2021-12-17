<template>
    <tbody>
        <tr>
          <td class="text-left">{{ formatDate(createdAt) }}</td>
          <td class="text-left">{{ transactionType }}</td>
          <td class="text-left">$ {{ amount }}</td>
        </tr>
    </tbody>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  props: {
    createdAt: {
      type: String,
      default: ''
    },
    amount: {
      type: Number,
      default: 0
    },
    transactionType: {
      type: String,
      default: ''
    },
    status: {
      type: String,
      default: 'Vigente'
    }
  },
  methods: {
    formatDate (date) {
      const d = new Date(date)

      const datestring = d.getDate() + '-' + (d.getMonth() + 1) + '-' + d.getFullYear()

      return datestring
    }
  }
})
</script>
