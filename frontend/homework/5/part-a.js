const os = require('os');
const http = require('http');
const fs = require('fs');

function getSystemInfo() {
    return {
        HostName: os.hostname(),
        OperatingSystem: os.platform(),
        Architecture: os.arch(),
        OSRelease: os.release(),
        Uptime: os.uptime(),
        NumberOfCPUCores: os.cpus().length,
        TotalMemory: os.totalmem(),
        FreeMemory: os.freemem(),
        CurrentWorkingDirectory: process.cwd()
    };
}

const systemInfo = getSystemInfo();
console.log(systemInfo);

function writeSystemInfoToFile(filename) {
    const introMessage = "Hello, my name is Bittu Kumar!\nHere is my system information:\n";
    const systemInfoWithIntro = introMessage + JSON.stringify(systemInfo, null, 2);
    fs.writeFileSync(filename, systemInfoWithIntro);
}


writeSystemInfoToFile('systemInfo.json');

const server = http.createServer((req, res) => {
    if (req.url === '/') {
        const introMessage = "Hello, my name is Bittu Kumar!\nHere is my system information:\n";
        const systemInfoString = JSON.stringify(systemInfo, null, 2);
        const responseMessage = introMessage + systemInfoString;

        res.writeHead(200, { 'Content-Type': 'text/plain' });
        res.end(responseMessage);
    } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('Not Found');
    }
});


const PORT = process.env.PORT || 5000;

// Start the server
server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
