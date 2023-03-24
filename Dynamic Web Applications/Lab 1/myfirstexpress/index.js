const express = require('express')
const app = express()
const dt = require('./module');

const port = 8000

app.get('/', function (req, res) {
    res.send('<h1>This is my home page</h1>')
})
app.get('/about', function (req, res) {
    res.send('<h1>This is my about page</h1>')
})
app.get('/contact', function(req, res) {
    res.send('<h1>This is my contact page</h1>')
})
app.get('/date', function (req, res) {
    res.send("The date and time are currently: " + dt.myDateTime())
})

app.listen(port, function() { console.log(`Example app listening on port ${port}!`)
})