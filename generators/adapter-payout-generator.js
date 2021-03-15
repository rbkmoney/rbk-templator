module.exports = {
    description: 'Create a java adapter-payout project structure',
    prompts: [
        {
            type: 'input',
            name: 'bank_name',
            message: 'Как называется банк с которым вы интегрируетесь? (adapter-*bank_name*-payout)',
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
            message: 'Введите описание вашего сервиса (maven.description):'
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
        // infrastructure files
        {
            type: 'add',
            path: '{{pathHelper path}}src/test/resources/logback-test.xml',
            templateFile: 'plop-templates/adapter/payout/logback-test.xml'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}Jenkinsfile',
            templateFile: 'plop-templates/adapter/payout/jenkinsfile'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}.gitignore',
            templateFile: 'plop-templates/gitignore-template'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}pom.xml',
            templateFile: 'plop-templates/adapter/payout/pom.xml'
        },
        // resources
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/resources/application.yml',
            templateFile: 'plop-templates/adapter/payout/src/resources/spring.yml'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/resources/fixture/errors.json',
            templateFile: 'plop-templates/adapter/payout/src/resources/error-mapping.json'
        },
        // code.main
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/{{properCase bank_name}}Application.java',
            templateFile: 'plop-templates/adapter/payout/src/java-app.java'
        },
        // code.client
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/RemoteClient.java',
            templateFile: 'plop-templates/adapter/payout/src/client/RemoteClient.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/RemoteClientImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/client/RemoteClientImpl.java'
        },
        // code.client.model
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/BaseRequest.java',
            templateFile: 'plop-templates/adapter/payout/src/client/model/BaseRequest.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/BaseResponse.java',
            templateFile: 'plop-templates/adapter/payout/src/client/model/BaseResponse.java'
        },
        // code.client.converter
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/converter/RequestConverter.java',
            templateFile: 'plop-templates/adapter/payout/src/client/converter/RequestConverter.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/converter/ResponseConverter.java',
            templateFile: 'plop-templates/adapter/payout/src/client/converter/ResponseConverter.java'
        },
        // code.config
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/properties/{{properCase bank_name}}Properties.java',
            templateFile: 'plop-templates/adapter/payout/src/config/properties/adapter-properties.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/AppConfig.java',
            templateFile: 'plop-templates/adapter/payout/src/config/AppConfig.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/ProcessorConfig.java',
            templateFile: 'plop-templates/adapter/payout/src/config/ProcessorConfig.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/RestTemplateConfig.java',
            templateFile: 'plop-templates/adapter/payout/src/config/RestTemplateConfig.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/ServiceConfig.java',
            templateFile: 'plop-templates/adapter/payout/src/config/ServiceConfig.java'
        },
        // code.model
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/model/EntryStateModelImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/model/EntryStateModelImpl.java'
        },
        {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/model/ExitStateModelImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/model/ExitStateModelImpl.java'
        },
        // code.test
        {
            type: 'add',
            path: '{{pathHelper path}}src/test/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/{{properCase bank_name}}ApplicationTest.java',
            templateFile: 'plop-templates/adapter/payout/src/java-app-test.java'
        },
        // build_utils
        (answers) => {
            if (answers.withBuildUtils) {
                let git = require('simple-git')(answers.path);
                git.init();
                git.subModule(["add", "-b", "master", "git@github.com:rbkmoney/build_utils.git", "build_utils"]);
            }
        },
    ]
};