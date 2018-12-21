'use strict';

module.exports = function (plop) {
    plop.setWelcomeMessage('CUSTOM'.red + ' Какой проект вам запилить?');
    plop.addHelper('pathHelper', function (p) {
        return p ? p + "/" : "";
    });
    // generators
    plop.setGenerator('java-proto', {
        description: 'Create a java protocol project structure',
        prompts: [
            {
                type: 'input',
                name: 'name',
                message: 'Как называется ваш протокол? (без "-proto")',
                validate: function (value) {
                    if (value.length === 0) { return "Вы не ввели имя!"; }
                    if ((/.*-proto.*/).test(value)) { return "Вы ввели имя протокола с \"-proto\"!"; }
                    return true;
                }
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
                path: 'proto/{{lowerCase name}}.thrift',
                templateFile: 'plop-templates/proto/proto.thrift'
            },
            {
                type: 'add',
                path: 'Jenkinsfile',
                templateFile: 'plop-templates/proto/proto-jenkinsfile'
            },
            (answers) => {
                if (answers.withBuildUtils) {
                    let git = require('simple-git')("");
                    git.subModule(["add", "-b", "master", "git@github.com:rbkmoney/build_utils.git", "build_utils"]);
                }
            },
            {
                type: 'add',
                path: 'Makefile',
                templateFile: 'plop-templates/proto/proto-jenkinsfile'
            }
        ]
    });











    plop.setGenerator('java-service', {
        description: 'Create a java service project structure',
        prompts: [
            {
                type: 'input',
                name: 'name',
                message: 'Как называется ваш протокол? (без "-proto")'
            }
        ],
        actions: [
            (answers) => {
                console.log(`You choose: \n
				- name: ${answers.name}`);
                return '';
            },
            {
                type: 'add',
                path: 'app/tests/{{lowerCase path}}/{{lowerCase name}}.thrift',
                templateFile: 'plop-templates/modules/proto.thrift'
            }
        ]
    });
};
