import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/info/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/refresh`, user).then(success).catch(fail);
}

async function logout(userId, success, fail) {
  await api.get(`/logout/${userId}`).then(success).catch(fail);
}

async function mRegister(user, success, fail) {
  console.log(user.userId);
  await api.post(`/member`,user).then(success).catch(fail);
}

async function mUpdate(user, success, fail) {
  await api.put(`/member`,user).then(success).catch(fail);
}

async function mDelete(userId, success, fail) {
  await api.delete(`/member/${userId}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, mRegister, mUpdate, mDelete };
