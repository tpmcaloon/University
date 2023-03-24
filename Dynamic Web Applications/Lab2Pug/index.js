// Import express and ejs

var express = require ('express')
var ejs = require('ejs')
var bodyParser = require('body-parser')
var pug = require('pug')

// Define our data
var shopData = {shopName: "Binge Drinking Drinks",
                productCategories:["Beer", " Wine", " Soft Drinks", " Hot Drinks"],
                shopLocations:["Aldgate, London E1 8QN ", " New Cross,  London SE14 6NW ", " Barnet, EN5 5XY ", " Camden, London "]}
// Create the express application object
const app = express()
const port = 8000

app.use(bodyParser.urlencoded({ extended: true }));

// Set the directory where Express will pick up HTML files
// __dirname will get the current directory
app.set('views', __dirname + '/views');

// Tell Express that we want to use EJS as the templating engine
app.set('view engine', 'ejs');

// Tells Express how we should process html files
// We want to use EJS's rendering engine
app.engine('html', ejs.renderFile);

// Handle our routes
app.get('/',function(req,res){
    res.render('index.pug', {shopData: shopData})
 });
 app.get('/about',function(req,res){
    res.render('about.pug', {shopData: shopData});
});
app.get('/search',function(req,res){
     res.render("search.pug", {shopData: shopData});
});
app.get('/search-result', function (req, res) {
         // TODO:search in the database
         res.send("You searched for: " + req.query.keyword)
});

app.get('/register', function (req,res) {
    res.render('register.pug', {shopData: shopData});
});
app.post('/registered', function (req,res) {
// saving data in database
res.send(' Hello '+ req.body.first + ' '+ req.body.last +' you are now registered! We will send an email to you at '+ req.body.email);
});

// Start the web app listening
app.listen(port, () => console.log(`Example app listening on port ${port}!`))