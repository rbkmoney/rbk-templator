'use strict';
let protoGenerator = require('./generators/proto-generator');
let serviceGenerator = require('./generators/service-generator');
let pathHelper = require('./helpers/path-helper');
let chalk = require('chalk');

module.exports = function (plop) {
    plop.setWelcomeMessage(chalk.blue("КАКОЙ ") + chalk.red("ПРОЕКТ ") + chalk.blue("ВАМ ") + chalk.red("ЗАПИЛИТЬ???"));

    // helpers
    plop.addHelper('pathHelper', pathHelper);

    // generators
    plop.setGenerator('java-proto', protoGenerator);
    plop.setGenerator('java-service', serviceGenerator);
};
