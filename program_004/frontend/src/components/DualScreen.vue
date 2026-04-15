<template>
  <section class="section-dual py-6 bg-light" id="dual">
    <div class="container">
      <div class="row align-items-center g-5">
        <div class="col-lg-6">
          <div class="dual-screen-demo">
            <div class="screen screen-left">
              <div class="screen-label">小屏 · 视频互动</div>
              <div class="screen-inner video-screen">
                <div class="play-btn"><i class="bi bi-play-fill"></i></div>
                <div class="progress-bar-demo mt-auto">
                  <div class="progress-track">
                    <div class="progress-fill"></div>
                  </div>
                  <div
                    class="d-flex justify-content-between small mt-1 text-dark fw-bold"
                  >
                    <span>12:34</span><span>45:00</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="screen screen-right">
              <div class="screen-label">大屏 · 实际操作</div>
              <div class="screen-inner editor-screen">
                <div class="mini-code">
                  <div class="mc-line mc-purple">
                    function solve(n) &#123;
                  </div>
                  <div class="mc-line mc-blue">
                    &nbsp;&nbsp;let dp = [0, 1];
                  </div>
                  <div class="mc-line mc-green">
                    &nbsp;&nbsp;for (let i=2; i&lt;=n; i++)
                  </div>
                  <div class="mc-line mc-blue">
                    &nbsp;&nbsp;&nbsp;&nbsp;dp[i] = dp[i-1] + dp[i-2];
                  </div>
                  <div class="mc-line mc-purple">
                    &nbsp;&nbsp;return dp[n];
                  </div>
                  <div class="mc-line mc-purple">&#125;</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <span class="section-label">双屏学习</span>
          <h2 class="section-title">看视频的同时<br />同步动手实操</h2>
          <p class="section-desc">
            双屏学习，小屏视频互动，大屏实际操作，学习无障碍。打开浏览器，即刻开始编程！
          </p>
          <div class="row g-3 mt-2">
            <div class="col-sm-6">
              <div class="mini-feature-card">
                <i class="bi bi-tv text-primary fs-4 mb-2"></i>
                <div class="fw-semibold">同步讲解</div>
                <div class="small text-muted">视频与代码同步高亮</div>
              </div>
            </div>
            <div class="col-sm-6">
              <div class="mini-feature-card">
                <i class="bi bi-chat-dots text-primary fs-4 mb-2"></i>
                <div class="fw-semibold">实时互动</div>
                <div class="small text-muted">随时提问，即时解答</div>
              </div>
            </div>
            <div class="col-sm-6">
              <div class="mini-feature-card">
                <i class="bi bi-bookmark-check text-primary fs-4 mb-2"></i>
                <div class="fw-semibold">进度追踪</div>
                <div class="small text-muted">自动记录学习进度</div>
              </div>
            </div>
            <div class="col-sm-6">
              <div class="mini-feature-card">
                <i class="bi bi-layout-split text-primary fs-4 mb-2"></i>
                <div class="fw-semibold">灵活布局</div>
                <div class="small text-muted">自由调整分屏比例</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
</script>
