<template>
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand fw-bold" href="#">
        <i class="bi bi-cloud-fill me-2"></i>云端编程
      </a>
      <button
        class="navbar-toggler border-0"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navMenu"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navMenu">
        <ul class="navbar-nav ms-auto gap-lg-3">
          <li class="nav-item"><a class="nav-link" href="#lab">实验室</a></li>
          <li class="nav-item">
            <a class="nav-link" href="#dual">双屏学习</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#features">课程特色</a>
          </li>
          <li class="nav-item">
            <a
              class="nav-link btn btn-outline-light px-3 rounded-pill"
              href="#"
              >立即开始</a
            >
          </li>
          <li class="nav-item">
            <button
              v-if="!loggedInUser"
              class="nav-link btn btn-login px-3 rounded-pill"
              @click="$emit('openAuth')"
            >登录 / 注册</button>
            <div v-else class="nav-user-container">
              <span class="nav-link nav-user">
                <i class="bi bi-person-check-fill me-1"></i>{{ loggedInUser }}
              </span>
              <button class="btn btn-logout px-3 rounded-pill" @click="$emit('logout')">登出</button>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
defineProps({ loggedInUser: String })
defineEmits(['openAuth', 'logout'])
</script>

<style scoped>
.btn-login {
  background: #ff6b6b;
  color: #fff;
  border: 2px solid #1e293b;
  font-weight: 700;
  cursor: pointer;
  box-shadow: 3px 3px 0px #1e293b;
  transition: transform 0.1s, box-shadow 0.1s;
}
.btn-login:hover {
  transform: translate(1px, 1px);
  box-shadow: 1px 1px 0px #1e293b;
  color: #fff;
}
.nav-user-container {
  display: flex;
  align-items: center;
  gap: 1rem;
}
.nav-user {
  font-weight: 700;
  color: #ff6b6b;
  padding: 0.5rem 0;
}
.btn-logout {
  background: #f8f9fa;
  color: #1e293b;
  border: 2px solid #1e293b;
  font-weight: 700;
  cursor: pointer;
  box-shadow: 2px 2px 0px #1e293b;
  transition: transform 0.1s, box-shadow 0.1s;
  padding: 0.3rem 1rem;
  font-size: 0.9rem;
}
.btn-logout:hover {
  transform: translate(1px, 1px);
  box-shadow: 1px 1px 0px #1e293b;
  background: #e2e8f0;
}
</style>
