#!/usr/bin/env node

'use strict';

const fs = require('fs')
const path = require('path');
const root = path.dirname(require.main.filename);
const script = fs.readFileSync(root + "/scripts/wetkitty-templator.sh", "utf8")

const shell = require('shelljs');
shell.exec(script)