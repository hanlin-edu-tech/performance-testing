const express = require('express')
const app = express()
app.use(express.json())

app.post('/hello', (req, res) => {
    let ip = findIp(req)
    let referer = findReferer(req)
    let userAgent = findUserAgent(req)
    let result = {
        user: req.body.user,
        message: req.body.message,
        ip: ip,
        referer: referer,
        userAgent: userAgent
    }
    res.send(result)
})

app.listen(8080, () => {
    console.log('App listening on port 8080!');
})

const findIp = (req) => req.headers['x-forwarded-for'] || req.socket.remoteAddress
const findReferer = (req) => req.headers['referer'] || req.headers['referrer'] || ''
const findUserAgent = (req) => req.headers['user-agent'] || ''
