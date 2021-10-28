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
          type: 'list',
          name: 'language',
          choices: ['Java', 'Kotlin'],
          default: 'Java',
          message: 'Выберите язык:'
        },
        {
          type: 'confirm',
          name: 'doc',
          default: true,
          message: 'Оставить комментарии к коду?'
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
    actions: function(data) {
      let actions = [];

      if (data.language === 'Kotlin') {
        actions.push(
            // code.main
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/Adapter{{properCase bank_name}}PayoutApplication.kt',
              templateFile: 'plop-templates/adapter/payout/src/kotlin-app.kt'
            },
            // code.client
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/RemoteClient.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/RemoteClient.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/RemoteClientImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/RemoteClientImpl.kt'
            },
            // code.client.model
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/BaseRequest.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/model/BaseRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/BaseResponse.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/model/BaseResponse.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/MoneyTransferRequest.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/model/MoneyTransferRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/StatusRequest.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/model/StatusRequest.kt'
            },
            // code.client.converter
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/converter/RequestConverter.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/converter/RequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/converter/ResponseConverter.kt',
              templateFile: 'plop-templates/adapter/payout/src/client/converter/ResponseConverter.kt'
            },
            // code.config
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/properties/Adapter{{properCase bank_name}}PayoutProperties.kt',
              templateFile: 'plop-templates/adapter/payout/src/config/properties/AdapterProperties.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/properties/RestTemplateProperties.kt',
              templateFile: 'plop-templates/adapter/payout/src/config/properties/RestTemplateProperties.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/AppConfig.kt',
              templateFile: 'plop-templates/adapter/payout/src/config/AppConfig.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/ProcessorConfig.kt',
              templateFile: 'plop-templates/adapter/payout/src/config/ProcessorConfig.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/RestTemplateConfig.kt',
              templateFile: 'plop-templates/adapter/payout/src/config/RestTemplateConfig.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/ServiceConfig.kt',
              templateFile: 'plop-templates/adapter/payout/src/config/ServiceConfig.kt'
            },
            // code.constant
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/constant/UrlPaths.kt',
              templateFile: 'plop-templates/adapter/payout/src/constant/UrlPaths.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/constant/OptionsField.kt',
              templateFile: 'plop-templates/adapter/payout/src/constant/OptionsField.kt'
            },
            // code.flow
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/flow/StepResolverImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/flow/StepResolverImpl.kt'
            },
            // code.handler
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/CallbackHandler.kt',
              templateFile: 'plop-templates/adapter/payout/src/handler/CallbackHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/GetQuoteHandlerImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/handler/GetQuoteHandlerImpl.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/MoneyTransferHandler.kt',
              templateFile: 'plop-templates/adapter/payout/src/handler/MoneyTransferHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/StatusHandler.kt',
              templateFile: 'plop-templates/adapter/payout/src/handler/StatusHandler.kt'
            },
            // code.model
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/model/EntryStateModelImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/model/EntryStateModelImpl.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/model/ExitStateModelImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/model/ExitStateModelImpl.kt'
            },
            // code.processor
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/processor/ErrorProcessor.kt',
              templateFile: 'plop-templates/adapter/payout/src/processor/ErrorProcessor.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/processor/SuccessProcessor.kt',
              templateFile: 'plop-templates/adapter/payout/src/processor/SuccessProcessor.kt'
            },
            // code.converter.entry
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/entry/WithdrawalToEntryConverterImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/converter/entry/WithdrawalToEntryConverterImpl.kt'
            },
            // code.converter.exit
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/exit/ExitToProcessResultConverterImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/converter/exit/ExitToProcessResultConverterImpl.kt'
            },
            // code.converter.request
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/request/EntryStateModelToMoneyTransferRequestConverter.kt',
              templateFile: 'plop-templates/adapter/payout/src/converter/request/EntryStateModelToMoneyTransferRequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/request/EntryStateModelToStatusRequestConverter.kt',
              templateFile: 'plop-templates/adapter/payout/src/converter/request/EntryStateModelToStatusRequestConverter.kt'
            },
            // code.servlet
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/servlet/AdapterServlet.kt',
              templateFile: 'plop-templates/adapter/payout/src/servlet/AdapterServlet.kt'
            },
            // code.validator
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/validator/ValidatorImpl.kt',
              templateFile: 'plop-templates/adapter/payout/src/validator/ValidatorImpl.kt'
            },
            // code.test
            {
              type: 'add',
              path: '{{pathHelper path}}src/test/kotlin/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/Adapter{{properCase bank_name}}PayoutApplicationTest.kt',
              templateFile: 'plop-templates/adapter/payout/src/kotlin-app-test.kt'
            },
          );
      } else {
        actions.push(
          // code.main
          {
            type: 'add',
              path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/Adapter{{properCase bank_name}}PayoutApplication.java',
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
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/MoneyTransferRequest.java',
            templateFile: 'plop-templates/adapter/payout/src/client/model/MoneyTransferRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/client/model/StatusRequest.java',
            templateFile: 'plop-templates/adapter/payout/src/client/model/StatusRequest.java'
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
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/config/properties/Adapter{{properCase bank_name}}PayoutProperties.java',
            templateFile: 'plop-templates/adapter/payout/src/config/properties/AdapterProperties.java'
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
          // code.constant
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/constant/UrlPaths.java',
            templateFile: 'plop-templates/adapter/payout/src/constant/UrlPaths.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/constant/OptionsField.java',
            templateFile: 'plop-templates/adapter/payout/src/constant/OptionsField.java'
          },
          // code.flow
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/flow/StepResolverImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/flow/StepResolverImpl.java'
          },
          // code.handler
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/CallbackHandler.java',
            templateFile: 'plop-templates/adapter/payout/src/handler/CallbackHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/GetQuoteHandlerImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/handler/GetQuoteHandlerImpl.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/MoneyTransferHandler.java',
            templateFile: 'plop-templates/adapter/payout/src/handler/MoneyTransferHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/handler/StatusHandler.java',
            templateFile: 'plop-templates/adapter/payout/src/handler/StatusHandler.java'
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
          // code.processor
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/processor/ErrorProcessor.java',
            templateFile: 'plop-templates/adapter/payout/src/processor/ErrorProcessor.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/processor/SuccessProcessor.java',
            templateFile: 'plop-templates/adapter/payout/src/processor/SuccessProcessor.java'
          },
          // code.converter.entry
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/entry/WithdrawalToEntryConverterImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/converter/entry/WithdrawalToEntryConverterImpl.java'
          },
          // code.converter.exit
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/exit/ExitToProcessResultConverterImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/converter/exit/ExitToProcessResultConverterImpl.java'
          },
          // code.converter.request
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/request/EntryStateModelToMoneyTransferRequestConverter.java',
            templateFile: 'plop-templates/adapter/payout/src/converter/request/EntryStateModelToMoneyTransferRequestConverter.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/converter/request/EntryStateModelToStatusRequestConverter.java',
            templateFile: 'plop-templates/adapter/payout/src/converter/request/EntryStateModelToStatusRequestConverter.java'
          },
          // code.servlet
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/servlet/AdapterServlet.java',
            templateFile: 'plop-templates/adapter/payout/src/servlet/AdapterServlet.java'
          },
          // code.validator
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/validator/ValidatorImpl.java',
            templateFile: 'plop-templates/adapter/payout/src/validator/ValidatorImpl.java'
          },
          // code.test
          {
            type: 'add',
            path: '{{pathHelper path}}src/test/java/com/rbkmoney/{{adapterPayoutDirCase bank_name}}/Adapter{{properCase bank_name}}PayoutApplicationTest.java',
            templateFile: 'plop-templates/adapter/payout/src/java-app-test.java'
          }
          );
      }

      actions.push(
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
        (answers) => {
          if (answers.withBuildUtils) {
            let git = require('simple-git')(answers.path);
            git.init();
            git.subModule(["add", "-b", "master", "git@github.com:rbkmoney/build_utils.git", "build_utils"]);
          }
        }
      )

      return actions;
    }
};
