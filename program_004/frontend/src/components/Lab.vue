<template>
  <section class="section-lab py-6" id="lab">
    <div class="container">
      <div class="row align-items-center g-5">
        <div class="col-lg-6 order-lg-2">
          <div class="browser-mockup">
            <div class="browser-bar">
              <span></span><span></span><span></span>
              <div class="browser-url ms-3">cloud-lab.artech.com</div>
            </div>
            <div class="browser-body">
              <div class="code-editor">
                <div class="editor-sidebar">
                  <div class="file-item active">
                    <i class="bi bi-file-code me-2"></i>main.py
                  </div>
                  <div class="file-item">
                    <i class="bi bi-file-code me-2"></i>utils.py
                  </div>
                  <div class="file-item">
                    <i class="bi bi-folder me-2"></i>assets/
                  </div>
                </div>
                <div class="editor-main">
                  <div class="code-line">
                    <span class="kw">import</span> <span class="mod">os</span>
                  </div>
                  <div class="code-line">
                    <span class="kw">from</span>
                    <span class="mod">flask</span>
                    <span class="kw">import</span> Flask
                  </div>
                  <div class="code-line">&nbsp;</div>
                  <div class="code-line">
                    <span class="fn">app</span> = Flask(__name__)
                  </div>
                  <div class="code-line">&nbsp;</div>
                  <div class="code-line">
                    <span class="dec">@app.route</span>(<span class="str"
                      >'/'</span
                    >)
                  </div>
                  <div class="code-line">
                    <span class="kw">def</span>
                    <span class="fn">index</span>():
                  </div>
                  <div class="code-line">
                    &nbsp;&nbsp;&nbsp;&nbsp;<span class="kw">return</span>
                    <span class="str">"Hello, Cloud!"</span>
                  </div>
                  <div class="code-line cursor-line">
                    &nbsp;<span class="cursor">|</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-6 order-lg-1">
          <span class="section-label">云端编程实验室</span>
          <h2 class="section-title">每人独享<br />完全隔离的编程实验室</h2>
          <p class="section-desc">
            每人拥有自己完全独立的编程实验室，内置所有基础软件及学习素材。打开浏览器，即刻开始编程！
          </p>
          <ul class="feature-list">
            <li>
              <i class="bi bi-check-circle-fill text-primary me-2"></i
              >独立容器化环境，互不干扰
            </li>
            <li>
              <i class="bi bi-check-circle-fill text-primary me-2"></i
              >预装主流开发工具与语言运行时
            </li>
            <li>
              <i class="bi bi-check-circle-fill text-primary me-2"></i
              >代码实时保存，随时续学
            </li>
            <li>
              <i class="bi bi-check-circle-fill text-primary me-2"></i
              >零配置，开箱即用
            </li>
          </ul>
          <a href="#" class="btn btn-primary rounded-pill px-4 mt-2">
            进入实验室 <i class="bi bi-arrow-right ms-1"></i>
          </a>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
</script>
