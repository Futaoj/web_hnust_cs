<template>
  <Header
    :loggedInUser="loggedInUser"
    @openAuth="showAuth = true"
    @logout="handleLogout"
  />
  <Hero @showInfo="openInfo" />
  <Lab />
  <DualScreen />
  <Feature />
  <Footer />

  <InfoDialog
    :isVisible="infoVisible"
    :message="infoMessage"
    @close="infoVisible = false"
  />

  <AuthDialog
    :isVisible="showAuth"
    @close="showAuth = false"
    @loginSuccess="handleLogin"
  />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Header from './components/Header.vue'
import Hero from './components/Hero.vue'
import Lab from './components/Lab.vue'
import DualScreen from './components/DualScreen.vue'
import Feature from './components/Feature.vue'
import Footer from './components/Footer.vue'
import InfoDialog from './components/dialogs/InfoDialog.vue'
import AuthDialog from './components/dialogs/AuthDialog.vue'

const infoVisible = ref(false)
const infoMessage = ref('')
const showAuth = ref(false)
const loggedInUser = ref('')

function openInfo(msg) {
  infoMessage.value = msg
  infoVisible.value = true
}

function handleLogin(username) {
  loggedInUser.value = username
}

async function handleLogout() {
  try {
    const res = await fetch('/api/logout', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      credentials: 'include'
    })
    const data = await res.json()
    if (data.success) {
      loggedInUser.value = ''
      openInfo('您已成功登出。')
    }
  } catch (err) {
    console.error('Logout failed:', err)
  }
}

onMounted(() => {
  window.addEventListener("scroll", () => {
    const nav = document.getElementById("mainNav")
    if (nav) {
      nav.classList.toggle("scrolled", window.scrollY > 40)
    }
  })

  document.querySelectorAll('a[href^="#"]').forEach((a) => {
    a.addEventListener("click", (e) => {
      const target = document.querySelector(a.getAttribute("href"))
      if (target) {
        e.preventDefault()
        target.scrollIntoView({ behavior: "smooth", block: "start" })
      }
    })
  })

  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((e) => {
        if (e.isIntersecting) e.target.classList.add("visible")
      })
    },
    { threshold: 0.1 },
  )
  document
    .querySelectorAll(".feature-card, .mini-feature-card, .section-title, .section-desc")
    .forEach((el) => observer.observe(el))
})
</script>
