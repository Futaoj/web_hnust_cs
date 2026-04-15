import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      // 将 /api/* 代理到 Java 后端（Spring Boot on 8080）
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        // 保持 Cookie（Session）跟随请求
      }
    }
  }
})
