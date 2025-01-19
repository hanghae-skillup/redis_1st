const https = require('http'); // 'http' for non-HTTPS APIs. Use 'https' for secure APIs.

const apiUrl = 'http://localhost:8080/movies/now-showing/cached';
// const apiUrl = 'http://localhost:8080/movies/now-showing';
const title = 'Movie 1';
const genre = null;

const fullUrl = `${apiUrl}?title=${encodeURIComponent(title)}`;

const options = {
  method: 'GET',
};

console.log(`Calling API: ${fullUrl}`);

const req = https.request(fullUrl, options, (res) => {
  console.log(`Status Code: ${res.statusCode}`);

  let data = '';
  res.on('data', (chunk) => {
    data += chunk;
  });

  res.on('end', () => {
    if (res.statusCode === 200) {
      console.log('Response Data:', JSON.parse(data));
    } else {
      console.log(`API call failed with status code: ${res.statusCode}`);
      console.log('Response Message:', data);
    }
  });
});

req.on('error', (error) => {
  console.error('Error making API request:', error.message);
});

req.end();
