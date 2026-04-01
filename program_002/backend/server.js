const express = require('express');
const cors = require('cors');
const session = require('express-session');
const bodyParser = require('body-parser');
const svgCaptcha = require('svg-captcha');
const db = require('./database');

const app = express();
const PORT = 3000;

app.use(cors({
    origin: 'http://localhost:5173',
    credentials: true
}));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(session({
    secret: 'program_002_secret',
    resave: false,
    saveUninitialized: true,
    cookie: { secure: false }
}));

app.get('/api/captcha', (req, res) => {
    const captcha = svgCaptcha.create();
    req.session.captcha = captcha.text.toLowerCase();
    res.type('svg');
    res.status(200).send(captcha.data);
});

app.post('/api/register', (req, res) => {
    const { username, password, captcha } = req.body;

    if (!captcha || !req.session.captcha || captcha.toLowerCase() !== req.session.captcha) {
        return res.status(400).json({ success: false, message: 'Invalid captcha' });
    }

    if (!username || !password) {
        return res.status(400).json({ success: false, message: 'Username and password are required' });
    }

    const insert = 'INSERT INTO users (username, password) VALUES (?, ?)';
    db.run(insert, [username, password], function(err) {
        if (err) {
            if (err.message.includes('UNIQUE constraint failed')) {
                return res.status(400).json({ success: false, message: 'Username already exists' });
            }
            return res.status(500).json({ success: false, message: 'Database error' });
        }
        res.json({ success: true, message: 'Registration successful' });
    });
});

app.post('/api/login', (req, res) => {
    const { username, password, captcha } = req.body;

    if (!captcha || !req.session.captcha || captcha.toLowerCase() !== req.session.captcha) {
        return res.status(400).json({ success: false, message: 'Invalid captcha' });
    }

    const query = 'SELECT * FROM users WHERE username = ? AND password = ?';
    db.get(query, [username, password], (err, row) => {
        if (err) {
            return res.status(500).json({ success: false, message: 'Database error' });
        }
        if (row) {
            res.json({ success: true, message: 'Login successful', user: { id: row.id, username: row.username } });
        } else {
            res.status(401).json({ success: false, message: 'Invalid username or password' });
        }
    });
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
