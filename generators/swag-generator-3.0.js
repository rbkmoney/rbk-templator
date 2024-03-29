const scriptFiles = [
    "plop-templates/swag/scripts/*.js"
];

const componentFiles = [
    "plop-templates/swag/spec/components/securitySchemes/*.yaml"
];

const pluginFiles = [
  "plop-templates/swag/spec/plugins/*.js"
];

const responseFiles = [
  "plop-templates/swag/spec/responses/*.yaml"
];

const schemaFiles = [
  "plop-templates/swag/spec/components/schemas/*.yaml"
];

module.exports = {
    description: 'Create a swag OpenAPI 3.0 project structure',
    prompts: [
        {
            type: 'input',
            name: 'name',
            message: 'Как называется ваш проект?',
            validate: value => {
                if (value.length === 0) {
                    return "Вы не ввели имя!";
                }
                return true;
            }
        },
        {
            type: 'input',
            name: 'description',
            message: 'Введите описание вашего проекта:'
        },
        {
            type: 'input',
            name: 'path',
            message: 'В какой директории создать шаблон? [.]'
        },
        {
            type: 'confirm',
            name: 'withBuildUtils',
            message: 'Хотите ли вы подключить build_utils?'
        }
    ],
    actions: [
        {
            type: 'add',
            path: '{{pathHelper path}}wercker.yml',
            templateFile: 'plop-templates/swag/swag-wercker.yml'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}pom.xml',
            templateFile: 'plop-templates/swag/swag-pom.xml'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}package.json',
            templateFile: 'plop-templates/swag/swag-package.json'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}Makefile',
            templateFile: 'plop-templates/swag/swag-makefile'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}Jenkinsfile',
            templateFile: 'plop-templates/swag/swag-jenkinsfile'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}.gitignore',
            templateFile: 'plop-templates/swag-gitignore-template'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}gulpfile.js',
            templateFile: 'plop-templates/swag/swag-gulpfile.js'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}web/index.html',
            templateFile: 'plop-templates/swag/web/index.html'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}spec/openapi.yaml',
            templateFile: 'plop-templates/swag/spec/openapi.yaml'
        },
        {
            type: 'addMany',
            destination: "{{pathHelper path}}",
            base: 'plop-templates/swag',
            templateFiles: scriptFiles
        },
        {
            type: 'addMany',
            destination: "{{pathHelper path}}/spec",
            base: 'plop-templates/swag/spec/',
            templateFiles: componentFiles
        },
        {
            type: 'addMany',
            destination: "{{pathHelper path}}/spec",
            base: 'plop-templates/swag/spec/',
            templateFiles: pluginFiles
        },
        {
            type: 'addMany',
            destination: "{{pathHelper path}}/spec",
            base: 'plop-templates/swag/spec/',
            templateFiles: responseFiles
        },
        {
            type: 'addMany',
            destination: "{{pathHelper path}}/spec",
            base: 'plop-templates/swag/spec/',
            templateFiles: schemaFiles
        },
        (answers) => {
            if (answers.withBuildUtils) {
                let git = require('simple-git')(answers.path);
                git.init();
                git.subModule(["add", "-b", "master", "git@github.com:rbkmoney/build_utils.git", "build_utils"]);
            }
        }
    ]
};
