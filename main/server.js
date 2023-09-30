const express = require('express');
const path = require('path');

const app = express();
const port = 5050;

const publicDirectoryPath = path.join(__dirname, '/public');
console.log(__dirname);
console.log(publicDirectoryPath);

app.use(express.static(publicDirectoryPath));

const server = app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});

// Stop the server when needed
// server.close();
