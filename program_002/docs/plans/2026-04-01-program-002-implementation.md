# Program 002 Vue Fullstack Refactor Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Create a full-stack Vue.js + Express application from the `program_001` HTML file, featuring componentization, event handling, and a SQLite-backed login/captcha system.

**Architecture:** Monorepo with two main folders (`frontend` and `backend`). The frontend uses Vite+Vue3. The backend uses Express, `svg-captcha`, and `sqlite3`. The frontend communicates with the backend via REST APIs.

**Tech Stack:** Vue 3, Vite, Node.js, Express, SQLite3, svg-captcha, cors

---

### Task 1: Backend Setup & API (Auth + Captcha)

**Files:**
- Create: `backend/package.json`
- Create: `backend/server.js`
- Create: `backend/database.js`

**Step 1: Initialize Backend & Install Dependencies**
```bash
cd /Users/wanghan/Projects/web_hnust_cs/program_002
mkdir backend && cd backend
npm init -y
npm install express cors sqlite3 svg-captcha express-session body-cookie
```
*(Wait, just `express cors sqlite3 svg-captcha express-session body-parser` is fine)*

**Step 2: Create Database Model (`database.js`)**
Set up SQLite, create a `users` table, and insert a default user (admin/admin123).

**Step 3: Create Express Server (`server.js`)**
Setup Express with CORS (allowing credentials) and Session. Create three endpoints:
- `GET /api/captcha`: generates `svg-captcha`, stores the text in session, returns the SVG.
- `POST /api/login`: checks session captcha vs provided captcha. If valid, checks SQLite db.
- `POST /api/register`: checks captcha, inserts into DB.

**Step 4: Run server locally to verify**
Run: `node server.js`
Expected: Server starts on port 3000.

**Step 5: Commit**
```bash
git add backend/
git commit -m "feat: backend api for auth and captcha with sqlite"
```

---

### Task 2: Frontend Setup & Component Extraction

**Files:**
- Create: `frontend/*` (Vite output)
- Create: `frontend/src/components/Header.vue`, `Hero.vue`, `Lab.vue`, `DualScreen.vue`, `Features.vue`, `Footer.vue`

**Step 1: Initialize Vite Project**
```bash
cd /Users/wanghan/Projects/web_hnust_cs/program_002
npm create vite@latest frontend -- --template vue
cd frontend
npm install
```

**Step 2: Copy Assets and Styles**
Copy `style.css` from `program_001` to `frontend/src/assets/style.css` and import it in `main.js`. Update `index.html` to include Bootstrap and Google Fonts.

**Step 3: Extract Vue Components**
Break the `program_001` index.html into the 6 components listed above. Replace the content of `App.vue` with these components.

**Step 4: Verify Frontend Locally**
Run: `npm run dev`
Expected: The exact same visual layout as `program_001` loads in the Vite dev server.

**Step 5: Commit**
```bash
git add frontend/
git commit -m "feat: setup vue frontend and extract ui components"
```

---

### Task 3: Info Dialog & Global Event Handling

**Files:**
- Create: `frontend/src/components/InfoDialog.vue`
- Modify: `frontend/src/components/Hero.vue`
- Modify: `frontend/src/App.vue`

**Step 1: Create `InfoDialog.vue`**
Create a neo-brutalism styled modal (glassmorphism background, 3D bouncy card, `v-if`/`<Transition>` for pop-in animation). It takes a `message` prop and an `isVisible` prop, emitting a `close` event.

**Step 2: Add Event Listeners in `Hero.vue`**
Add `@click="emit('showInfo', '正在为您加载课程内容...')"` to the "马上学习" and "了解" buttons.

**Step 3: Handle State in `App.vue`**
Provide a reactive state to show the `InfoDialog` when those buttons are clicked.

**Step 4: Verify visually**
Click the buttons and see the bouncy neo-brutalism dialog appear instead of a standard alert.

**Step 5: Commit**
```bash
git add frontend/
git commit -m "feat: add animated neo-brutalism info dialog for hero buttons"
```

---

### Task 4: Auth Dialog & Backend Integration

**Files:**
- Create: `frontend/src/components/AuthDialog.vue`
- Modify: `frontend/src/components/Header.vue`
- Modify: `frontend/src/App.vue`

**Step 1: Create `AuthDialog.vue`**
Create a modal with tabs for Login/Register.
Include fields: Username, Password, Captcha.
Include an `<img>` tag for the captcha that hits `http://localhost:3000/api/captcha`. Clicking the image refreshes the URL (`?t=timestamp`).

**Step 2: Bind API Logic**
Use `fetch` or `axios` (with `credentials: 'include'`) to `POST /api/login` and `/api/register`.
If the captcha is wrong, trigger a CSS shake animation on the input and refresh the captcha.
If successful, emit a success event, close the modal, and change the Header state from "Login" to "Welcome, {user}".

**Step 3: Connect to Header**
In `Header.vue`, clicking the Login button emits an event to open `AuthDialog`.

**Step 4: Verify Full Flow**
Run both backend and frontend. Try logging in with the default admin user. Verify captcha validation.

**Step 5: Commit**
```bash
git add frontend/
git commit -m "feat: integrate vue auth dialog with express backend and captcha"
```