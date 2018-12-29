'use strict';
const protoGenerator = require('./generators/proto-generator');
const serviceGenerator = require('./generators/service-generator');
const pathHelper = require('./helpers/path-helper');
const chalk = require('chalk');

module.exports = function (plop) {
    plop.setWelcomeMessage(chalk.blue("КАКОЙ ") + chalk.red("ПРОЕКТ ") + chalk.blue("ВАМ ") + chalk.red("ЗАПИЛИТЬ???"));

    // helpers
    plop.addHelper('pathHelper', pathHelper);

    // generators
    plop.setGenerator('java-proto', protoGenerator);
    plop.setGenerator('java-service', serviceGenerator);
};
