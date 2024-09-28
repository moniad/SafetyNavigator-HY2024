const express = require('express')
const app = express()
const port = 3000
const path = require("node:path")

app.use(express.static(path.resolve('../frontend/build/')));

app.get('/', function (req, res) {

    const fileName = path.resolve('../frontend/build/index.html');
    res.sendFile(fileName);
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})