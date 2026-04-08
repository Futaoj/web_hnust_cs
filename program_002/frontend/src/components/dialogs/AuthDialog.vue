<template>
  <Transition name="dialog">
    <div v-if="isVisible" class="dialog-overlay" @click.self="closeDialog">
      <div class="auth-card">
        <!-- Close button -->
        <button class="close-btn" @click="closeDialog">✕</button>

        <!-- Tabs -->
        <div class="tabs">
          <button :class="['tab-btn', { active: mode === 'login' }]" @click="mode = 'login'">登录</button>
          <button :class="['tab-btn', { active: mode === 'register' }]" @click="mode = 'register'">注册</button>
        </div>

        <form @submit.prevent="handleSubmit">
          <div class="field">
            <label>用户名</label>
            <input v-model="username" type="text" placeholder="请输入用户名" required />
          </div>
          <div class="field">
            <label>密码</label>
            <input v-model="password" type="password" placeholder="请输入密码" required />
          </div>
          <div class="field">
            <label>验证码</label>
            <div class="captcha-row">
              <input
                v-model="captchaInput"
                type="text"
                placeholder="请输入验证码"
                :class="{ shake: captchaShake }"
                required
              />
              <img
                :src="captchaUrl"
                alt="验证码"
                class="captcha-img"
                @click="refreshCaptcha"
                title="点击刷新"
              />
            </div>
          </div>

          <div v-if="errorMsg" class="error-msg">{{ errorMsg }}</div>
          <div v-if="successMsg" class="success-msg">{{ successMsg }}</div>

          <button type="submit" class="submit-btn" :disabled="loading">
            {{ loading ? '处理中...' : (mode === 'login' ? '登录' : '注册') }}
          </button>
        </form>
      </div>
    </div>
  </Transition>
</template>

<script setup>
import { ref, watch } from 'vue'

const props = defineProps({
  isVisible: Boolean,
})
const emit = defineEmits(['close', 'loginSuccess'])

const mode = ref('login')
const username = ref('')
const password = ref('')
const captchaInput = ref('')
const captchaUrl = ref('')
const captchaShake = ref(false)
const errorMsg = ref('')
const successMsg = ref('')
const loading = ref(false)

function refreshCaptcha() {
  captchaUrl.value = `http://localhost:3000/api/captcha?t=${Date.now()}`
}

function closeDialog() {
  errorMsg.value = ''
  successMsg.value = ''
  emit('close')
}

watch(() => props.isVisible, (val) => {
  if (val) {
    refreshCaptcha()
    errorMsg.value = ''
    successMsg.value = ''
    username.value = ''
    password.value = ''
    captchaInput.value = ''
  }
})

watch(mode, () => {
  errorMsg.value = ''
  successMsg.value = ''
  captchaInput.value = ''
  refreshCaptcha()
})

async function handleSubmit() {
  errorMsg.value = ''
  successMsg.value = ''
  loading.value = true

  const url = mode.value === 'login'
    ? 'http://localhost:3000/api/login'
    : 'http://localhost:3000/api/register'

  try {
    const res = await fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      credentials: 'include',
      body: JSON.stringify({
        username: username.value,
        password: password.value,
        captcha: captchaInput.value,
      }),
    })

    const data = await res.json()

    if (data.success) {
      if (mode.value === 'login') {
        successMsg.value = `欢迎回来，${data.user.username}！`
        emit('loginSuccess', data.user.username)
        setTimeout(closeDialog, 1000)
      } else {
        successMsg.value = '注册成功！请登录。'
        mode.value = 'login'
        captchaInput.value = ''
        refreshCaptcha()
      }
    } else {
      errorMsg.value = data.message || '操作失败'
      if (data.message && data.message.toLowerCase().includes('captcha')) {
        triggerCaptchaShake()
      }
      refreshCaptcha()
      captchaInput.value = ''
    }
  } catch {
    errorMsg.value = '网络错误，请稍后再试'
  } finally {
    loading.value = false
  }
}

function triggerCaptchaShake() {
  captchaShake.value = true
  setTimeout(() => { captchaShake.value = false }, 600)
}
</script>

<style scoped>
.dialog-overlay {
  position: fixed;
  inset: 0;
  background: rgba(30, 41, 59, 0.5);
  backdrop-filter: blur(8px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9998;
}

.auth-card {
  position: relative;
  background: #fff4e6;
  border: 3px solid #1e293b;
  border-radius: 24px;
  box-shadow: 8px 8px 0px #1e293b;
  padding: 2rem;
  width: 90%;
  max-width: 420px;
}

.close-btn {
  position: absolute;
  top: 1rem;
  right: 1rem;
  background: none;
  border: 2px solid #1e293b;
  border-radius: 50%;
  width: 32px;
  height: 32px;
  font-size: 0.85rem;
  font-weight: 800;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background 0.15s;
}
.close-btn:hover { background: #ff6b6b; color: #fff; }

.tabs {
  display: flex;
  gap: 0.5rem;
  margin-bottom: 1.5rem;
}

.tab-btn {
  flex: 1;
  padding: 0.5rem;
  border: 2px solid #1e293b;
  border-radius: 12px;
  font-weight: 700;
  font-size: 1rem;
  background: #fff;
  cursor: pointer;
  transition: background 0.15s, color 0.15s, box-shadow 0.15s;
}
.tab-btn.active {
  background: #ff6b6b;
  color: #fff;
  box-shadow: 3px 3px 0px #1e293b;
}

.field {
  margin-bottom: 1rem;
}
.field label {
  display: block;
  font-weight: 700;
  margin-bottom: 0.3rem;
  font-size: 0.9rem;
  color: #1e293b;
}
.field input {
  width: 100%;
  padding: 0.55rem 0.9rem;
  border: 2px solid #1e293b;
  border-radius: 10px;
  font-size: 1rem;
  background: #fff;
  outline: none;
  transition: box-shadow 0.2s, border-color 0.2s;
}
.field input:focus {
  border-color: #ff6b6b;
  box-shadow: 0 0 0 3px rgba(255,107,107,0.25);
}

.captcha-row {
  display: flex;
  gap: 0.6rem;
  align-items: center;
}
.captcha-row input { flex: 1; }
.captcha-img {
  height: 42px;
  border: 2px solid #1e293b;
  border-radius: 8px;
  cursor: pointer;
  flex-shrink: 0;
}

.error-msg {
  background: #ffe4e4;
  color: #c0392b;
  border: 2px solid #c0392b;
  border-radius: 8px;
  padding: 0.4rem 0.8rem;
  font-size: 0.9rem;
  font-weight: 600;
  margin-bottom: 0.8rem;
}
.success-msg {
  background: #d4f8e8;
  color: #1a7a4a;
  border: 2px solid #1a7a4a;
  border-radius: 8px;
  padding: 0.4rem 0.8rem;
  font-size: 0.9rem;
  font-weight: 600;
  margin-bottom: 0.8rem;
}

.submit-btn {
  width: 100%;
  padding: 0.7rem;
  background: #ff6b6b;
  color: #fff;
  border: 3px solid #1e293b;
  border-radius: 50px;
  font-size: 1.05rem;
  font-weight: 800;
  box-shadow: 4px 4px 0px #1e293b;
  cursor: pointer;
  transition: transform 0.1s, box-shadow 0.1s;
  margin-top: 0.5rem;
}
.submit-btn:hover:not(:disabled) {
  transform: translate(2px, 2px);
  box-shadow: 2px 2px 0px #1e293b;
}
.submit-btn:disabled { opacity: 0.6; cursor: not-allowed; }

/* Shake animation for wrong captcha */
@keyframes shake {
  0%, 100% { transform: translateX(0); }
  20%       { transform: translateX(-8px); }
  40%       { transform: translateX(8px); }
  60%       { transform: translateX(-5px); }
  80%       { transform: translateX(5px); }
}
.shake { animation: shake 0.5s ease-in-out; }

/* Dialog transition */
.dialog-enter-active {
  animation: bounceIn 0.4s cubic-bezier(0.34, 1.56, 0.64, 1);
}
.dialog-leave-active {
  animation: bounceIn 0.25s cubic-bezier(0.34, 1.56, 0.64, 1) reverse;
}
@keyframes bounceIn {
  from { transform: scale(0.6); opacity: 0; }
  to   { transform: scale(1);   opacity: 1; }
}
</style>
