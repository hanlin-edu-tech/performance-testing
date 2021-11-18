const fastify = require('fastify')({ logger: false })

fastify.post('/hello', async (req, reply) => {
    let ip = findIp(req);
    let referer = findReferer(req);
    let userAgent = findUserAgent(req);
    return {
        ip: ip,
        referer: referer,
        userAgent: userAgent,
        user: req.body.user,
        message: req.body.message
    }
})

const start = async () => {
    try {
        await fastify.listen(8080, '0.0.0.0')
    } catch (err) {
        fastify.log.error(err)
        process.exit(1)
    }
}
start()

const findIp = (req) => req.headers['x-forwarded-for'] || req.connection.remoteAddress
const findReferer = (req) => req.headers['referer'] || req.headers['referrer'] || ""
const findUserAgent = (req) => req.headers['user-agent'] || ""
