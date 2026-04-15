<template>
  <section class="hero d-flex align-items-center" id="hero">
    <div class="hero-overlay"></div>
    <div class="container position-relative text-center py-5">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <span class="badge badge-vibrant rounded-pill px-3 py-2 mb-4 fs-6">
            <i class="bi bi-stars me-1"></i>全新云端学习体验
          </span>
          <h1 class="display-3 fw-black lh-sm mb-4">
            云端编程<br />
            <span class="text-gradient">浏览器里边学边练</span>
          </h1>
          <p class="lead mb-5 opacity-90">
            软件定义一切，网络连接时空，学习软件技术，创造未来世界。
          </p>
          <div
            class="d-flex flex-column flex-sm-row gap-3 justify-content-center"
          >
            <a href="#lab" class="btn btn-primary btn-lg rounded-pill px-5" @click.prevent="$emit('showInfo', '💖 提示：您已经成功点击，正在为您加载精彩内容！')">
              <i class="bi bi-play-circle-fill me-2"></i>立即体验
            </a>
            <a
              href="#features"
              class="btn btn-outline-dark btn-lg rounded-pill px-5"
              @click.prevent="$emit('showInfo', '💖 提示：您已经成功点击，正在为您加载精彩内容！')"
            >
              了解更多 <i class="bi bi-arrow-right ms-1"></i>
            </a>
          </div>
        </div>
      </div>
      <!-- Stats row -->
      <div class="row justify-content-center mt-5 g-4">
        <div class="col-6 col-md-3">
          <div class="stat-card p-3 rounded-3">
            <div class="fs-2 fw-bold">10,000+</div>
            <div class="small opacity-75">注册学员</div>
          </div>
        </div>
        <div class="col-6 col-md-3">
          <div class="stat-card p-3 rounded-3">
            <div class="fs-2 fw-bold">200+</div>
            <div class="small opacity-75">精品课程</div>
          </div>
        </div>
        <div class="col-6 col-md-3">
          <div class="stat-card p-3 rounded-3">
            <div class="fs-2 fw-bold">99%</div>
            <div class="small opacity-75">好评率</div>
          </div>
        </div>
        <div class="col-6 col-md-3">
          <div class="stat-card p-3 rounded-3">
            <div class="fs-2 fw-bold">24/7</div>
            <div class="small opacity-75">在线服务</div>
          </div>
        </div>
      </div>
    </div>
    <!-- Scroll hint -->
    <div class="scroll-hint">
      <i class="bi bi-chevron-double-down"></i>
    </div>
  </section>
</template>

<script setup>
defineEmits(['showInfo'])
</script>
