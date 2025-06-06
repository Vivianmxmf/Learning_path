const express = require('express');
const app = express();

app.get('/', (req,res)=>{
    res.send("WELCOME");
});

app.listen(3000,function(){
    console.log("app listeniing on port 3000");
});



