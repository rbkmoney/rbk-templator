#!/usr/bin/env node

'use strict';

const path = require('path');

const { spawn } = require('child_process');

spawn('node', [path.join(__dirname, './node_modules/.bin/plop'),
    '--plopfile', path.join(__dirname, './plopfile.js')], {
    cwd: process.cwd(),
    stdio: 'inherit' // Will use process .stdout, .stdin, .stderr
});
