# 云端编程 (Cloud Programming) - 组件化与全栈交互设计方案 (Vue3 + Node.js)

## 1. 概述与核心理念 (Overview)
本方案针对 `/web_hnust_cs/program_002` 进行开发。目标是使用 Vue 3 将此前重构过的落地页拆分为可复用的前端组件，并实现与后端 Node.js (Express) 的数据绑定与交互。新增功能包括：登录注册的图形验证码、定制化的信息弹出框。

整体 UI/UX 将严格继承 **“活泼教育 / 游戏化 (Neo-Brutalism)”** 风格（大圆角、粗黑边框、深厚阴影、高饱和色彩与弹性动画）。

## 2. 系统架构 (Architecture)
采用前后端分离 (Monorepo-style in one folder) 架构：
*   `/program_002/frontend`: Vue 3 + Vite 前端。使用纯 CSS（继承之前的样式结构），使用 Fetch/Axios 与后端通信。
*   `/program_002/backend`: Node.js + Express 后端。使用 SQLite3 进行本地微型持久化存储，使用 `svg-captcha` 配合 `express-session` 生成并校验图形验证码。

## 3. 前端组件拆分 (Frontend Components)
将现有的 `index.html` 拆分为以下 Vue 单文件组件 (SFCs)：
1.  **`App.vue`**: 根组件，组装各个子组件。
2.  **`components/Header.vue`**: 顶部导航栏，包含“登录/注册”按钮。
3.  **`components/Hero.vue`**: 首屏，包含“马上学习”与“了解”按钮。
4.  **`components/Lab.vue`**: 云端实验室板块。
5.  **`components/DualScreen.vue`**: 双屏学习板块。
6.  **`components/Feature.vue`**: 课程特色板块。
7.  **`components/Footer.vue`**: 页脚。
8.  **`components/dialogs/AuthDialog.vue`**: 全局登录/注册模态框（支持验证码和表单切换）。
9.  **`components/dialogs/InfoDialog.vue`**: 全局消息提示框，用于替代原生的 `alert()`。

## 4. 后端接口设计 (API Endpoints)
*   `GET /api/captcha`: 生成并返回包含 SVG 内容的图形验证码，并将文本值存入当前用户的 Session 中。
*   `POST /api/register`: 接收 `{ username, password, captcha }`。验证 Session 中的验证码，成功则存入 SQLite 数据库。
*   `POST /api/login`: 接收 `{ username, password, captcha }`。验证 Session 中的验证码，比对数据库账号密码。

## 5. UI/UX 体验细节 (ui-ux-pro-max 级别)
*   **模态框背景**: `AuthDialog` 与 `InfoDialog` 弹出时，背景使用毛玻璃效果 (`backdrop-filter: blur(8px)`)，框体使用带有粗阴影的 3D 卡片效果。
*   **交互动画**: 组件入场和出场使用 Vue `<Transition>` (结合 CSS `@keyframes bounceIn`) 制作“Q弹”动画。
*   **输入框体验**: 输入框 (`input`) 获取焦点时阴影扩大，边框变色；验证码错误时，输入框触发横向晃动 (`shake`) 动画。
*   **信息弹出**: 点击首屏“马上学习”或“了解”按钮时，触发 `InfoDialog.vue`，显示“💖 提示：您已经成功点击，正在为您加载精彩内容！”。

## 6. 开发步骤 (Tech Stack & Execution)
1.  **后端初始化**: `npm init`, 安装 `express`, `cors`, `sqlite3`, `svg-captcha`, `express-session`。
2.  **前端初始化**: `npm create vite@latest frontend -- --template vue`。
3.  **移植样式**: 将 `program_001` 的 `style.css` 和 HTML 结构移植到对应的 Vue 组件中。
4.  **开发业务逻辑**: 完成 Auth 和 Dialog 组件及其状态管理（使用 Composition API `ref`/`reactive` 或原生的 Provide/Inject）。
5.  **联调**: 验证前后端登录、注册与验证码流程。