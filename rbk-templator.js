#!/usr/bin/env node

'use strict';

const path = require('path');
const {exec, execSync, spawn} = require('child_process');


async function checkForUpdates() {
    const {error, stdout, stderr} = await exec('npm outdated -g | grep \"@pospolitanv/rbk-templator\"');

    return new Promise((res) => {
        stdout.on('data', (data) => {
            if (data) {
                console.log("Update is needed!");
                res("Update needed")
            }
        })
        if (error || stderr) {
            res("No update needed")
        }
    })
}

checkForUpdates()
    .then((res) => {
        if (res === "Update needed") {
            console.log("Updating...")
            execSync('npm update -g @pospolitanv/rbk-templator');
            return Promise.resolve("Updated");
        }
        return Promise.resolve("kekes")
    })
    .then((res) => {
        if (res !== "Updated") {
            spawn('node',
                [path.join(__dirname, './node_modules/.bin/plop'), '--plopfile', path.join(__dirname, './plopfile.js')],
                {cwd: process.cwd(), stdio: 'inherit'}
            );
        } else {
            console.log("Updated. Please, run command again.")
        }
    });
