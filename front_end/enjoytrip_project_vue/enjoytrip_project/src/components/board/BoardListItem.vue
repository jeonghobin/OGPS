<template>
  <tr>
    <td>{{ article.article_no }}</td>
    <td>
      <router-link :to="{ name: 'boardview', params: { articleno: article.article_no } }">
        {{ article.subject }}
      </router-link>
    </td>
    <td>{{ article.user_id }}</td>
    <td>{{ article.register_time }}</td>
    <td>{{ article.hit }}</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  props: {
    article: Object,
  },
};
</script>

<style></style>
