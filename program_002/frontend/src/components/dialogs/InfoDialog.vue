<template>
  <Transition name="dialog">
    <div v-if="isVisible" class="dialog-overlay" @click.self="$emit('close')">
      <div class="dialog-card">
        <div class="dialog-icon">💖</div>
        <p class="dialog-message">{{ message }}</p>
        <button class="dialog-btn" @click="$emit('close')">好的，知道了！</button>
      </div>
    </div>
  </Transition>
</template>

<script setup>
defineProps({
  isVisible: Boolean,
  message: String,
})
defineEmits(['close'])
</script>

<style scoped>
.dialog-overlay {
  position: fixed;
  inset: 0;
  background: rgba(30, 41, 59, 0.45);
  backdrop-filter: blur(8px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.dialog-card {
  background: #fff4e6;
  border: 3px solid #1e293b;
  border-radius: 24px;
  box-shadow: 8px 8px 0px #1e293b;
  padding: 2.5rem 2rem;
  text-align: center;
  max-width: 380px;
  width: 90%;
}

.dialog-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.dialog-message {
  font-size: 1.1rem;
  font-weight: 700;
  color: #1e293b;
  margin-bottom: 1.5rem;
  line-height: 1.5;
}

.dialog-btn {
  background: #ff6b6b;
  color: #fff;
  border: 3px solid #1e293b;
  border-radius: 50px;
  padding: 0.6rem 2rem;
  font-size: 1rem;
  font-weight: 800;
  box-shadow: 4px 4px 0px #1e293b;
  cursor: pointer;
  transition: transform 0.1s, box-shadow 0.1s;
}

.dialog-btn:hover {
  transform: translate(2px, 2px);
  box-shadow: 2px 2px 0px #1e293b;
}

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
