<template>
    <tr>
        <td><img :src="area.first_image" width="100"></td>
        <td>{{area.title}}</td>
        <td>{{area.addr1}} {{area.addr2}}</td>
        <td>{{area.latitude}}</td>
        <td>{{area.longitude}}</td>
	</tr>
</template>

<script>
export default {
    name: 'AttractionList',
    components: {},
    props:{
        area:Object,
    },
    data() {
        return {
            message: '',
        };
    },
    created() {
    },
    methods: {},
};
</script>

<style scoped></style>