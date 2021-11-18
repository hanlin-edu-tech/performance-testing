const express = require('express');
const app = express();
app.use(express.json());

app.post('/hello', (req, res) => {
    let ip = findIp(req);
    let referer = findReferer(req);
    let userAgent = findUserAgent(req);
    let result = {
        ip: ip,
        referer: referer,
        userAgent: userAgent,
        user: req.body.user,
        message: req.body.message
    }
    res.send(result);
});

app.listen(8080, () => console.log('App listening on port 8080!'));

const findIp = (req) => req.headers['x-forwarded-for'] || req.connection.remoteAddress
const findReferer = (req) => req.headers['referer'] || req.headers['referrer'] || ""
const findUserAgent = (req) => req.headers['user-agent'] || ""
