const functions = require("firebase-functions");
const axios = require("axios");

// Create and Deploy Your First Cloud Functions
// https://firebase.google.com/docs/functions/write-firebase-functions

exports.helloWorld = functions.https.onRequest((request, response) => {
    functions.logger.info("Hello logs!", {structuredData: true});
    response.send("Hello from Firebase!");
});

exports.api = functions.https.onRequest(async (request, response) => {
    switch (request.method) {
        case "GET":
          // eslint-disable-next-line no-case-declarations
            const res = await axios.get('https://jsonplaceholder.typicode.com/users/1');
            response.send(res.data);
            break;
        case "POST":
          // eslint-disable-next-line no-case-declarations

            break;
        case "PUT":
            response.send("It was a PUT request");
            break;
        case "DELETE":
            response.send("It was a DELETE request");
            break;
        default:
            response.send("It was a " + request.method + " request");
    }
});

exports.userAdded = functions.auth.user().onCreate((user) => {
    console.log(`user ${user.email} is created`);
    return Promise.resolve();
});

exports.scheduledFunction = functions.pubsub.schedule('* * * * *').onRun(() => {
    console.log('This will be run every minutes!');
    return Promise.resolve();
});
