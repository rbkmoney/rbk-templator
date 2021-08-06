const pluginFiles = [
  "plop-templates/openapi/plugins/*.js"
];

const responseFiles = [
  "plop-templates/openapi/components/responses/*.yaml"
];

const schemaFiles = [
  "plop-templates/openapi/components/schemas/*.yaml"
];

const parameterFiles = [
  "plop-templates/openapi/components/parameters/*.yaml"
];

const securitySchemaFiles = [
  "plop-templates/openapi/components/security-schemes/*.yaml"
];

const docFiles = [
  "plop-templates/openapi/docs/*.md"
];

module.exports = {
  description: 'Create a OpenAPI 3.0 project structure',
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
      path: '{{pathHelper path}}pom.xml',
      templateFile: 'plop-templates/openapi/openapi-pom.xml'
    },
    {
      type: 'add',
      path: '{{pathHelper path}}package.json',
      templateFile: 'plop-templates/openapi/openapi-package.json'
    },
    {
      type: 'add',
      path: '{{pathHelper path}}Makefile',
      templateFile: 'plop-templates/openapi/openapi-makefile'
    },
    {
      type: 'add',
      path: '{{pathHelper path}}Jenkinsfile',
      templateFile: 'plop-templates/openapi/openapi-jenkinsfile'
    },
    {
      type: 'add',
      path: '{{pathHelper path}}.gitignore',
      templateFile: 'plop-templates/openapi-gitignore-template'
    },
    {
      type: 'add',
      path: '{{pathHelper path}}web/index.html',
      templateFile: 'plop-templates/openapi/web/index.html'
    },
    {
      type: 'add',
      path: '{{pathHelper path}}openapi/openapi.yaml',
      templateFile: 'plop-templates/openapi/openapi.yaml'
    },
    {
      type: 'add',
      path: '{{pathHelper path}}.redocly.yaml',
      templateFile: 'plop-templates/openapi/openapi-redocly.yaml'
    },
    {
      type: 'addMany',
      destination: "{{pathHelper path}}/openapi",
      base: 'plop-templates/openapi/',
      templateFiles: securitySchemaFiles
    },
    {
      type: 'addMany',
      destination: "{{pathHelper path}}",
      base: 'plop-templates/openapi/',
      templateFiles: pluginFiles
    },
    {
      type: 'addMany',
      destination: "{{pathHelper path}}/openapi",
      base: 'plop-templates/openapi/',
      templateFiles: responseFiles
    },
    {
      type: 'addMany',
      destination: "{{pathHelper path}}/openapi",
      base: 'plop-templates/openapi/',
      templateFiles: schemaFiles
    },
    {
      type: 'addMany',
      destination: "{{pathHelper path}}/openapi",
      base: 'plop-templates/openapi/',
      templateFiles: docFiles
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
