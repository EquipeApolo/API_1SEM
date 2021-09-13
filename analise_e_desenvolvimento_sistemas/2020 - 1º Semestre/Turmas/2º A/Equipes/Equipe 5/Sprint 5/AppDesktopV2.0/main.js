const { app, BrowserWindow } = require('electron');

function createWindow() {
  //Create browser window
  win = new BrowserWindow({
    width: 1200,
    height: 700,
    })
    //Load the index.html of the app
    win.loadFile('./src/index.html')  
  }

  app.on('ready', createWindow);