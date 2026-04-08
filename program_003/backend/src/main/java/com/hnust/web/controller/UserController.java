package com.hnust.web.controller;

import com.hnust.web.entity.User;
import com.hnust.web.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.imageio.ImageIO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Spring MVC 控制器
 * 处理验证码、登录、注册三个 REST 接口
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private static final String CAPTCHA_SESSION_KEY = "captcha_text";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /api/captcha
     * 生成图形验证码，将文本存入 Session，返回 PNG 图片流
     */
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 生成 4 位随机验证码（排除易混淆字符）
        String chars = "ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            text.append(chars.charAt(random.nextInt(chars.length())));
        }
        // 存入 Session，转小写统一比较
        session.setAttribute(CAPTCHA_SESSION_KEY, text.toString().toLowerCase());

        // 使用 Java 2D 绘制验证码图片
        int width = 120, height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 开启抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 背景色（Neo-Brutalism 米色）
        g.setColor(new Color(255, 244, 230));
        g.fillRect(0, 0, width, height);

        // 干扰线
        g.setStroke(new BasicStroke(1.5f));
        for (int i = 0; i < 4; i++) {
            g.setColor(new Color(random.nextInt(180) + 50, random.nextInt(180) + 50, random.nextInt(180) + 50));
            g.drawLine(random.nextInt(width), random.nextInt(height),
                       random.nextInt(width), random.nextInt(height));
        }

        // 绘制文字
        g.setFont(new Font("Arial", Font.BOLD, 22));
        int x = 8;
        for (char c : text.toString().toCharArray()) {
            // 随机颜色（深色系）
            g.setColor(new Color(random.nextInt(80), random.nextInt(80), random.nextInt(150) + 30));
            // 轻微旋转
            double angle = (random.nextDouble() - 0.5) * 0.4;
            g.rotate(angle, x + 10, 25);
            g.drawString(String.valueOf(c), x, 28);
            g.rotate(-angle, x + 10, 25);
            x += 26;
        }
        g.dispose();

        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(image, "png", response.getOutputStream());
    }

    /**
     * POST /api/login
     * 请求体：{ username, password, captcha }
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> body,
            HttpSession session) {

        Map<String, Object> result = new HashMap<>();

        // 验证码校验
        if (!validateCaptcha(body.get("captcha"), session)) {
            result.put("success", false);
            result.put("message", "Invalid captcha");
            return ResponseEntity.badRequest().body(result);
        }

        User user = userService.login(body.get("username"), body.get("password"));
        if (user != null) {
            result.put("success", true);
            result.put("message", "Login successful");
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", user.getId());
            userInfo.put("username", user.getUsername());
            result.put("user", userInfo);
            return ResponseEntity.ok(result);
        }

        result.put("success", false);
        result.put("message", "Invalid username or password");
        return ResponseEntity.status(401).body(result);
    }

    /**
     * POST /api/register
     * 请求体：{ username, password, captcha }
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody Map<String, String> body,
            HttpSession session) {

        Map<String, Object> result = new HashMap<>();

        // 验证码校验
        if (!validateCaptcha(body.get("captcha"), session)) {
            result.put("success", false);
            result.put("message", "Invalid captcha");
            return ResponseEntity.badRequest().body(result);
        }

        String username = body.get("username");
        String password = body.get("password");
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            result.put("success", false);
            result.put("message", "Username and password are required");
            return ResponseEntity.badRequest().body(result);
        }

        boolean success = userService.register(username, password);
        if (success) {
            result.put("success", true);
            result.put("message", "Registration successful");
            return ResponseEntity.ok(result);
        }

        result.put("success", false);
        result.put("message", "Username already exists");
        return ResponseEntity.badRequest().body(result);
    }

    // ---- private helper ----

    private boolean validateCaptcha(String input, HttpSession session) {
        String sessionCaptcha = (String) session.getAttribute(CAPTCHA_SESSION_KEY);
        return input != null && sessionCaptcha != null
               && input.trim().toLowerCase().equals(sessionCaptcha);
    }
}
