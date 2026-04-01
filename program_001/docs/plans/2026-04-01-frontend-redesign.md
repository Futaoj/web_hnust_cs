# Frontend Redesign Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Refactor the existing `/web_cs/program_001` landing page into a vibrant, gamified, Neo-Brutalism/Duolingo-style educational platform.

**Architecture:** Pure HTML/CSS updates replacing the dark/tech theme with a bright, playful, large-rounded-corner UI.

**Tech Stack:** HTML5, CSS3, Bootstrap 5 (CDN)

---

### Task 1: Base Typography and CSS Variables

**Files:**
- Modify: `index.html` (Google Fonts link)
- Modify: `style.css` (CSS variables, body font, reset)

**Step 1: Add Google Fonts**
Add `Nunito` or `Quicksand` to `index.html`.

**Step 2: Update CSS Variables**
Replace `--primary`, `--dark` etc., with vibrant colors (Orange, Mint Green, Coral) and Neo-brutalism shadow variables.

**Step 3: Verify visually**
Open `index.html` in browser (or via live server) and ensure the font has changed to a rounded sans-serif and the base background is light.

**Step 4: Commit**
```bash
git add index.html style.css
git commit -m "feat: setup base typography and neo-brutalism color palette"
```

---

### Task 2: Hero Section Redesign

**Files:**
- Modify: `index.html`
- Modify: `style.css`

**Step 1: HTML Structure**
Update the hero section background to use a bright color or an SVG wavy pattern. Add structural classes for floating elements. Update the CTA button to a 3D chunky button.

**Step 2: CSS Styles**
Write the `.hero` background styles, `.btn-primary` 3D styles (bottom `box-shadow` to simulate thickness), and `:active` state to translate Y down and reduce shadow.

**Step 3: Verify visually**
Check that the hero section no longer has a dark gradient and the button feels "pressable".

**Step 4: Commit**
```bash
git add index.html style.css
git commit -m "feat: redesign hero section with vibrant style and 3D buttons"
```

---

### Task 3: Lab Section (Browser Mockup) Redesign

**Files:**
- Modify: `index.html`
- Modify: `style.css`

**Step 1: HTML Structure**
Adjust classes in the `.browser-mockup` to feel more like a toy box.

**Step 2: CSS Styles**
Change `.browser-body` and `.editor-sidebar` to light, warm colors. Change the syntax highlighting colors (`.kw`, `.mod`, etc.) to a light-theme pastel palette. Add a thick neo-brutalism border (`border: 3px solid #1E293B`) to the mockup.

**Step 3: Verify visually**
Ensure the code editor looks like a playful, light-themed learning environment.

**Step 4: Commit**
```bash
git add index.html style.css
git commit -m "feat: restyle lab section into a light-themed playful editor"
```

---

### Task 4: Dual Screen Section Redesign

**Files:**
- Modify: `index.html`
- Modify: `style.css`

**Step 1: HTML Structure**
Change the `.dual-screen-demo` elements to look like floating dialogue bubbles or connected chunky cards.

**Step 2: CSS Styles**
Apply large `border-radius: 24px`, thick borders, and vibrant background colors (e.g., pastel yellow for video, pastel blue for code) to `.screen-left` and `.screen-right`. Update the play button to be huge and bubbly.

**Step 3: Verify visually**
Verify the dual screen looks friendly and connected.

**Step 4: Commit**
```bash
git add index.html style.css
git commit -m "feat: redesign dual screen layout with bubbly rounded cards"
```

---

### Task 5: Features Section Redesign

**Files:**
- Modify: `index.html`
- Modify: `style.css`

**Step 1: HTML Structure**
Update the icons to be larger, potentially emoji-based or using chunky colorful backgrounds.

**Step 2: CSS Styles**
Update `.feature-card` to have a 3D chunky shadow (e.g., `box-shadow: 0 8px 0 #1E293B`). Add a hover effect that presses the card down (`transform: translateY(4px); box-shadow: 0 4px 0 #1E293B`).

**Step 3: Verify visually**
Hover over the cards to ensure they have the physical "press" feeling.

**Step 4: Commit**
```bash
git add index.html style.css
git commit -m "feat: redesign feature cards with 3D pressable hover effects"
```

---

### Task 6: Final Polish and Micro-interactions

**Files:**
- Modify: `style.css`

**Step 1: CSS Animations**
Add smooth `spring` transitions (`cubic-bezier(0.34, 1.56, 0.64, 1)`) to all interactable elements. Refine the pop-in scroll animations.

**Step 2: Verify visually**
Scroll through the page and hover over all buttons/cards to ensure a cohesive, bouncy feel.

**Step 3: Commit**
```bash
git add style.css
git commit -m "style: add spring animations and final micro-interactions polish"
```