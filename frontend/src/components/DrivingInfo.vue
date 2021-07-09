<template>

<v-data-table
    :headers="headers"
    :items="drivingInfo"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'DrivingInfo',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
            { text: "userId", value: "userId" },
            { text: "destAddr", value: "destAddr" },
            { text: "allocStatus", value: "allocStatus" },
            { text: "drivingId", value: "drivingId" },
            { text: "drivingStatus", value: "drivingStatus" },
            { text: "startTime", value: "startTime" },
            { text: "finishTime", value: "finishTime" },
            { text: "fee", value: "fee" },
            { text: "paymentId", value: "paymentId" },
            { text: "paymentStatus", value: "paymentStatus" },
        ],
        drivingInfo : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/drivinginfos')

      this.drivingInfo = temp.data._embedded.drivinginfos;

    },
    methods: {
    }
  }
</script>

