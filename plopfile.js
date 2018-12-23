'use strict';
let protoGenerator = require('./generators/proto-generator');

module.exports = function (plop) {
    plop.setWelcomeMessage('CUSTOM'.red + ' Какой проект вам запилить?');
    plop.addHelper('pathHelper', function (path) {
        if (path && path.lastIndexOf("/") === (path.length - 1))
            path = path.substring(0, path.lastIndexOf("/"));
        return path ? path + "/" : "";
    });
    // generators
    plop.setGenerator('java-proto', protoGenerator);


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
