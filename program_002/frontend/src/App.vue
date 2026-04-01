<template>
  <Header />
  <Hero />
  <Lab />
  <DualScreen />
  <Feature />
  <Footer />
</template>

<script setup>
import { onMounted } from 'vue'
import Header from './components/Header.vue'
import Hero from './components/Hero.vue'
import Lab from './components/Lab.vue'
import DualScreen from './components/DualScreen.vue'
import Feature from './components/Feature.vue'
import Footer from './components/Footer.vue'

onMounted(() => {
  // Navbar scroll effect
  window.addEventListener("scroll", () => {
    const nav = document.getElementById("mainNav");
    if (nav) {
      nav.classList.toggle("scrolled", window.scrollY > 40);
    }
  });

  // Smooth scroll for anchor links
  document.querySelectorAll('a[href^="#"]').forEach((a) => {
    a.addEventListener("click", (e) => {
      const target = document.querySelector(a.getAttribute("href"));
      if (target) {
        e.preventDefault();
        target.scrollIntoView({ behavior: "smooth", block: "start" });
      }
    });
  });

  // Intersection Observer for fade-in
  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((e) => {
        if (e.isIntersecting) e.target.classList.add("visible");
      });
    },
    { threshold: 0.1 },
  );
  document
    .querySelectorAll(
      ".feature-card, .mini-feature-card, .section-title, .section-desc",
    )
    .forEach((el) => observer.observe(el));
})
</script>
