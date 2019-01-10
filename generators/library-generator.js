module.exports = {
    description: 'Create a java library project structure',
    prompts: [
        {
            type: 'input',
            name: 'name',
            message: 'Как называется ваша либа?',
            validate: function (value) {
                if (value.length === 0) {
                    return "Вы не ввели имя!";
                }
                return true;
            }
        },
        {
            type: 'input',
            name: 'description',
            message: 'Введите описание вашей либы (maven.description):'
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
            path: '{{pathHelper path}}Jenkinsfile',
            templateFile: 'plop-templates/library/library-jenkinsfile'
        },
        (answers) => {
            if (answers.withBuildUtils) {
                let git = require('simple-git')(answers.path);
                git.init();
                git.subModule(["add", "-b", "master", "git@github.com:rbkmoney/build_utils.git", "build_utils"]);
            }
        },
        {
            type: 'add',
            path: '{{pathHelper path}}.gitignore',
            templateFile: 'plop-templates/gitignore-template'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}pom.xml',
            templateFile: 'plop-templates/library/library-pom.xml'
        }
    ]
};