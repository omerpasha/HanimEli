
var express = require("express");
var app = express();
var bodyParser = require("body-parser");
const { Console } = require("console");
const { json } = require("body-parser");
const { ObjectId } = require("bson");
const mongoClient = require('mongodb').MongoClient;
var url = "Your Mongo API Key";
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.post('/magazaciProfil', (req, res) => {
    //bu endpoint image ve email kaydı oluşturur
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
           // console.log("mongo başladı magazaciProfil");
            const myDb = db.db('myDb');
            const collection = myDb.collection('magazaciProfil');
            const newUSer = {
                urun_sahibi_id: req.body.urun_sahibi_id,
                encodedImage: req.body.encodedImage,
                fiyat: req.body.fiyat,
                urun_ismi: req.body.urun_ismi,
                urun_sahibi_name:req.body.urun_sahibi_name,
                urun_sahibi_email:req.body.urun_sahibi_email

            }
            collection.insertOne(newUSer, (err, result) => {
                res.status(200).send();
                console.log("içeridema");

            })

        }
    })
})

app.post('/register', (req, res) => {

    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
            //console.log("mongo başladı");
            const myDb = db.db('myDb');
            const collection = myDb.collection('myTable');
            const newUSer = {
                name: req.body.name,
                email: req.body.email,
                password: req.body.password,
                rol: req.body.rol
            }
            collection.insertOne(newUSer, (err, result) => {
                res.status(200).send();
              //  console.log("içeridema");
            })

        }
    })
})
app.post('/login', (req, res) => {
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {

            const myDb = db.db('myDb');
            const collection = myDb.collection('myTable');

            //console.log("mongo login");

            const query = {
                email: req.body.email,
                password: req.body.password
            }
            collection.findOne(query, (err, result) => {
                if (result != null) {
                    const objToSend = {
                        id: result._id,
                        name: result.name,
                        rol: result.rol
                    }
                    //console.log("obje", objToSend);
                    res.status(200).send(JSON.stringify(objToSend));
                } else {
                    res.status(404).send();
                    console.log("böyle bir kayıt yok" + result);
                }
            })
        }
    })

})
app.post('/sepeteUrunEkleme', (req, res) => {
    //bu endpoint image ve email kaydı oluşturur
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
            console.log("mongo başladı sepeteurun");

            const myDb = db.db('myDb');
            const collection = myDb.collection('sepettekiler');
            //console.log("bodeyye" + req.body.fiyat);
            const newUSer = {
                urun_sahibi_id: req.body.urun_sahibi_id,
                urun_siparis_eden_id: req.body.urun_siparis_eden_id,
                encodedImage: req.body.encodedImage,
                fiyat: req.body.fiyat,
                urun_ismi: req.body.urun_ismi
            }

            console.log("siparis eden sepete urun ekleme"+ req.body.urun_siparis_eden_id);
            collection.insertOne(newUSer, (err, result) => {
                res.status(200).send();
            })

        }
    })
})
app.post('/sepetOnay', (req, res) => {
    //bu endpoint image ve email kaydı oluşturur
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
            console.log("mongo başladı sepet onay");

            const myDb = db.db('myDb');
            const collection = myDb.collection('onaylanan_sepet');
            //console.log("bodeyye" + req.body.fiyat);
            const newUSer = {
                urun_siparis_eden_id: req.body.urun_siparis_eden_id,
                encodedImage: req.body.encodedImage,
                urun_id:req.body.urun_id,
                fiyat: req.body.fiyat,
                urun_ismi: req.body.urun_ismi,
                urun_sahibi_id:req.body.urun_sahibi_id

            }
            console.log("onaylanan sepet"+ req.body.urun_ismi);
            collection.insertOne(newUSer, (err, result) => {
                res.status(200).send();
            })

        }
    })
})

app.post('/sepettenSil', (req, res) => {
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {

            const myDb = db.db('myDb');
            const collection = myDb.collection('sepettekiler');

            console.log("xxxxxx sepetten sil endpoint xxxx");

            
           console.log("gelen body =>"+req.body.id);
           
            collection.deleteOne({"_id":ObjectId(req.body.id)}, (err, result) => {
                res.status(200).send();
                console.log("içeridema");
            })

        }
    })

})

app.post('/magazaciUrunListelemeSil', (req, res) => {
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {

            const myDb = db.db('myDb');
            const collection = myDb.collection('magazaciProfil');

            console.log("xxxxxx sepetten sil endpoint xxxx");

            
           console.log("gelen body =>"+req.body.id);
           
            collection.deleteOne({"_id":ObjectId(req.body.id)}, (err, result) => {
                res.status(200).send();
                console.log("içeridema");
            })

        }
    })

})

app.post('/magazaciUrunListeleme', (req, res) => {
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
            console.log("geldiiiii")
            const myDb = db.db('myDb');
            console.log("mongo magazaciurunListeleme");
            //console.log(req.body.password + "=>>ahahah");
            const query = {
                urun_sahibi_id: req.body.urun_sahibi_id
            }
            console.log("bodyyy"+req.body)
            console.log("egegegegeg"+req.body.urun_sahibi_id)
            myDb.collection("magazaciProfil").find(query).toArray(function (err, result) {
                if (err) throw err;
                res.status(200).send(JSON.stringify({
                    urunler: result
                }));
                console.log("rerere"+result);
                db.close();
            });
        }
    })

})
app.post('/sepettekiUrunListeleme', (req, res) => {
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
            const myDb = db.db('myDb');
            console.log("mongo sepettekiUrunListeleme");
            
            myDb.collection("sepettekiler").find({}).toArray(function (err, result) {
                if (err) throw err;
                console.log("rrrr"+result)
                res.status(200).send(JSON.stringify({
                    urunler: result
                }));
                db.close();
            });
        }
    })

})
app.post('/musteriSiparisler', (req, res) => {
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
            const myDb = db.db('myDb');
            //console.log("mongo musteriSiparisler");
            //console.log(req.body.password + "=>>ahahah");
            myDb.collection("magazaciProfil").find({}).toArray(function (err, result) {
                if (err) throw err;
                res.status(200).send(JSON.stringify({
                    urunler: result
                }));
               // console.log(result);
                db.close();
            });
        }
    })

})


app.post('/siparisAlinanlar', (req, res) => {
    mongoClient.connect(url, (err, db) => {
        if (err) {
            console.log("error while connecting mongo client");
        } else {
            const myDb = db.db('myDb');
           // console.log("mongo musteriSiparisler");
            //console.log(req.body.password + "=>>ahahah");
            const query = {
                urun_sahibi_id: req.body.urun_sahibi_id
            }
            console.log("iddsdds"+req.body.urun_sahibi_id)
            myDb.collection("onaylanan_sepet").find(query).toArray(function (err, result) {
                if (err) throw err;
                res.status(200).send(JSON.stringify({
                    urunler: result
                }));
                //console.log("ressress  "+result);
                db.close();
            });
        }
    })

})


app.listen(8085, () => {
    console.log("hazır");
});


