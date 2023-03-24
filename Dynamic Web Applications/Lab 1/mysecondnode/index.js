const express = require('express')

var http = require('http');
var dt = require('./myfirstmodule');

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-type': 'text/html; charset=UTF-8'});
  res.write("The date and time are currently: " + dt.myDateTime());
  res.end('<h1>Hello World again!</h1>');
}).listen(8000, function () {
  console.log('Node server is running on PORT 8000...');
});