'use strict';
const protoGenerator = require('./generators/proto-generator');
const serviceGenerator = require('./generators/service-generator');
const libraryGenerator = require('./generators/library-generator');
const swagGeneratorOldSpec = require('./generators/swag-generator-2.0');
const swagGenerator = require('./generators/swag-generator-3.0');
const pathHelper = require('./helpers/path-helper');
const packageCase = require('./helpers/package-case-helper');
const chalk = require('chalk');

module.exports = function (plop) {
    plop.setWelcomeMessage(chalk.blue("КАКОЙ ") + chalk.red("ПРОЕКТ ") + chalk.blue("ВАМ ") + chalk.red("ЗАПИЛИТЬ???"));

    // helpers
    plop.addHelper('pathHelper', pathHelper);
    plop.addHelper('packageCase', packageCase);

    // generators
    plop.setGenerator('java-proto', protoGenerator);
    plop.setGenerator('java-service', serviceGenerator);
    plop.setGenerator('java-library', libraryGenerator);
    plop.setGenerator('swag-template-2.0', swagGeneratorOldSpec);
    plop.setGenerator('swag-template-3.0', swagGenerator);
};
