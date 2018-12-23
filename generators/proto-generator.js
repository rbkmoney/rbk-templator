module.exports = {
    description: 'Create a java protocol project structure',
    prompts: [
        {
            type: 'input',
            name: 'name',
            message: 'Как называется ваш проект-протокол? (без "-proto")',
            validate: function (value) {
                if (value.length === 0) {
                    return "Вы не ввели имя!";
                }
                if ((/.*-proto.*/).test(value)) {
                    return "Вы ввели имя протокола с \"-proto\"!";
                }
                return true;
            }
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
            path: '{{pathHelper path}}proto/{{lowerCase name}}.thrift',
            templateFile: 'plop-templates/proto/proto.thrift'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}Jenkinsfile',
            templateFile: 'plop-templates/proto/proto-jenkinsfile'
        },
        (answers) => {
            if (answers.withBuildUtils) {
                let git = require('simple-git')(answers.path);
                git.subModule(["add", "-b", "master", "git@github.com:rbkmoney/build_utils.git", "build_utils"]);
            }
        },
        {
            type: 'add',
            path: '{{pathHelper path}}Makefile',
            templateFile: 'plop-templates/proto/proto-makefile'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}.gitignore',
            templateFile: 'plop-templates/gitignore-template'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}pom.xml',
            templateFile: 'plop-templates/proto/proto-pom'
        }
    ]
};