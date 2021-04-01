'use strict';
const protoGenerator = require('./generators/proto-generator');
const serviceGenerator = require('./generators/service-generator');
const libraryGenerator = require('./generators/library-generator');
const swagGeneratorOldSpec = require('./generators/swag-generator-2.0');
const swagGenerator = require('./generators/swag-generator-3.0');
const adapterPayoutGenerator = require('./generators/adapter-payout-generator');
const equalsConditionHelper = require('./helpers/equals-condition-helper');
const pathHelper = require('./helpers/path-helper');
const packageCase = require('./helpers/package-case-helper');
const adapterPayoutDirCase = require('./helpers/adapter/payout/adapter-dir-case-helper');
const adapterPayoutPackageCase = require('./helpers/adapter/payout/adapter-package-case-helper');
const chalk = require('chalk');

module.exports = function (plop) {
    plop.setWelcomeMessage(chalk.blue("КАКОЙ ") + chalk.red("ПРОЕКТ ") + chalk.blue("ВАМ ") + chalk.red("ЗАПИЛИТЬ???"));

    // helpers
    plop.addHelper('if_eq', equalsConditionHelper);
    plop.addHelper('pathHelper', pathHelper);
    plop.addHelper('packageCase', packageCase);
    plop.addHelper('adapterPayoutDirCase', adapterPayoutDirCase);
    plop.addHelper('adapterPayoutPackageCase', adapterPayoutPackageCase);

    // generators
    plop.setGenerator('java-proto', protoGenerator);
    plop.setGenerator('java-service', serviceGenerator);
    plop.setGenerator('java-library', libraryGenerator);
    plop.setGenerator('java-adapter-payout', adapterPayoutGenerator);
    plop.setGenerator('swag-template-2.0', swagGeneratorOldSpec);
    plop.setGenerator('swag-template-3.0', swagGenerator);
};
