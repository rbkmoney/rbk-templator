module.exports = {
    description: 'Create a java adapter project structure',
    prompts: [
        {
            type: 'input',
            name: 'bank_name',
            message: 'Как называется банк с которым вы интегрируетесь? (adapter-*bank_name*)',
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
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/Adapter{{properCase bank_name}}Application.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/kotlin-app.kt'
            },
            // code.client
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/RemoteClient.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/RemoteClient.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/RemoteClientImpl.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/RemoteClientImpl.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/RemoteClientException.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/RemoteClientException.kt'
            },
            // code.client.model
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/BaseRequest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/BaseRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/BaseResponse.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/BaseResponse.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/PreAuthRequest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/PreAuthRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/AuthRequest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/AuthRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CaptureRequest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/CaptureRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/StatusRequest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/StatusRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/RecurrentRequest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/RecurrentRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CancelRequest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/CancelRequest.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/PreAuthResponse.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/PreAuthResponse.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/AuthResponse.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/AuthResponse.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CaptureResponse.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/CaptureResponse.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/StatusResponse.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/StatusResponse.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/RecurrentResponse.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/RecurrentResponse.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CancelResponse.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/model/CancelResponse.kt'
            },
            // code.client.converter
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/converter/RequestConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/converter/RequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/converter/ResponseConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/client/converter/ResponseConverter.kt'
            },
            // code.config
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/properties/Adapter{{properCase bank_name}}Properties.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/config/properties/AdapterProperties.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/properties/RestTemplateProperties.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/config/properties/RestTemplateProperties.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/RestTemplateConfig.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/config/RestTemplateConfig.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/AppConfig.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/config/AppConfig.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/ServerRestConfig.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/config/ServerRestConfig.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/ProcessorConfig.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/config/ProcessorConfig.kt'
            },
            // code.constant
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/constant/UrlPaths.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/constant/UrlPaths.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/constant/OptionsField.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/constant/OptionsField.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/constant/AdapterConstants.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/constant/AdapterConstants.kt'
            },
            // code.flow
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/flow/StepResolverImpl.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/flow/StepResolverImpl.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/backoff/SleepIntentHelper.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/backoff/SleepIntentHelper.kt'
            },
            // code.handler
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/StepHandler.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/handler/StepHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/AuthHandler.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/handler/AuthHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/PreAuthHandler.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/handler/PreAuthHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/CaptureHandler.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/handler/CaptureHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/CancelHandler.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/handler/CancelHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/RecurrentHandler.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/handler/RecurrentHandler.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/StatusHandler.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/handler/StatusHandler.kt'
            },
            // code.model
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/model/CustomEntryStateModel.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/model/CustomEntryStateModel.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/model/CustomExitStateModel.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/model/CustomExitStateModel.kt'
            },
            // code.processor
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/AuthProcessor.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/processor/AuthProcessor.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/CancelProcessor.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/processor/CancelProcessor.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/CaptureProcessor.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/processor/CaptureProcessor.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/PreAuthProcessor.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/processor/PreAuthProcessor.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/RecurrentProcessor.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/processor/RecurrentProcessor.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/StatusProcessor.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/processor/StatusProcessor.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/ErrorProcessor.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/processor/ErrorProcessor.kt'
            },
            // code.converter.entry
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/entry/PaymentContextToEntryModelConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/entry/PaymentContextToEntryModelConverter.kt'
            },
            // code.converter.exit
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/exit/ExitModelToProxyResultConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/exit/ExitModelToProxyResultConverter.kt'
            },
            // code.converter.request
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToPreAuthRequestConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToPreAuthRequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToAuthRequestConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToAuthRequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToCaptureRequestConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToCaptureRequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToCancelRequestConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToCancelRequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToRecurrentRequestConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToRecurrentRequestConverter.kt'
            },
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToStatusRequestConverter.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToStatusRequestConverter.kt'
            },
            // code.provider
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/service/Adapter{{properCase bank_name}}ProviderProxy.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/service/AdapterProviderProxy.kt'
            },
            // code.servlet
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/servlet/Adapter{{properCase bank_name}}Servlet.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/servlet/AdapterServlet.kt'
            },
            // code.validator
            {
              type: 'add',
              path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/validator/ValidatorImpl.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/validator/ValidatorImpl.kt'
            },
            // code.test
            {
              type: 'add',
              path: '{{pathHelper path}}src/test/kotlin/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/Adapter{{properCase bank_name}}ApplicationTest.kt',
              templateFile: 'plop-templates/adapter/acquiring/src/kotlin-app-test.kt'
            },
          );
      } else {
        actions.push(
          // code.main
          {
            type: 'add',
              path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/Adapter{{properCase bank_name}}Application.java',
            templateFile: 'plop-templates/adapter/acquiring/src/java-app.java'
          },
          // code.client
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/RemoteClient.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/RemoteClient.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/RemoteClientImpl.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/RemoteClientImpl.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/RemoteClientException.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/RemoteClientException.java'
          },
          // code.client.converter
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/converter/RequestConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/converter/RequestConverter.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/converter/ResponseConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/converter/ResponseConverter.java'
          },
          // code.client.model
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/BaseRequest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/BaseRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/BaseResponse.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/BaseResponse.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/AuthRequest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/AuthRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/PreAuthRequest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/PreAuthRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CaptureRequest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/CaptureRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/StatusRequest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/StatusRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/RecurrentRequest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/RecurrentRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CancelRequest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/CancelRequest.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/AuthResponse.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/AuthResponse.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/PreAuthResponse.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/PreAuthResponse.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CaptureResponse.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/CaptureResponse.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/StatusResponse.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/StatusResponse.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/RecurrentResponse.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/RecurrentResponse.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/client/model/CancelResponse.java',
            templateFile: 'plop-templates/adapter/acquiring/src/client/model/CancelResponse.java'
          },
          // code.config
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/properties/Adapter{{properCase bank_name}}Properties.java',
            templateFile: 'plop-templates/adapter/acquiring/src/config/properties/AdapterProperties.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/AppConfig.java',
            templateFile: 'plop-templates/adapter/acquiring/src/config/AppConfig.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/RestTemplateConfig.java',
            templateFile: 'plop-templates/adapter/acquiring/src/config/RestTemplateConfig.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/properties/RestTemplateProperties.java',
            templateFile: 'plop-templates/adapter/acquiring/src/config/properties/RestTemplateProperties.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/config/ProcessorConfig.java',
            templateFile: 'plop-templates/adapter/acquiring/src/config/ProcessorConfig.java'
          },
          // code.constant
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/constant/UrlPaths.java',
            templateFile: 'plop-templates/adapter/acquiring/src/constant/UrlPaths.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/constant/OptionsField.java',
            templateFile: 'plop-templates/adapter/acquiring/src/constant/OptionsField.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/constant/AdapterConstants.java',
            templateFile: 'plop-templates/adapter/acquiring/src/constant/AdapterConstants.java'
          },
          // code.flow
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/flow/StepResolverImpl.java',
            templateFile: 'plop-templates/adapter/acquiring/src/flow/StepResolverImpl.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/backoff/SleepIntentHelper.java',
            templateFile: 'plop-templates/adapter/acquiring/src/backoff/SleepIntentHelper.java'
          },
          // code.handler
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/StepHandler.java',
            templateFile: 'plop-templates/adapter/acquiring/src/handler/StepHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/AuthHandler.java',
            templateFile: 'plop-templates/adapter/acquiring/src/handler/AuthHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/PreAuthHandler.java',
            templateFile: 'plop-templates/adapter/acquiring/src/handler/PreAuthHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/CaptureHandler.java',
            templateFile: 'plop-templates/adapter/acquiring/src/handler/CaptureHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/CancelHandler.java',
            templateFile: 'plop-templates/adapter/acquiring/src/handler/CancelHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/RecurrentHandler.java',
            templateFile: 'plop-templates/adapter/acquiring/src/handler/RecurrentHandler.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/handler/StatusHandler.java',
            templateFile: 'plop-templates/adapter/acquiring/src/handler/StatusHandler.java'
          },
          // code.model
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/model/CustomEntryStateModel.java',
            templateFile: 'plop-templates/adapter/acquiring/src/model/CustomEntryStateModel.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/model/CustomExitStateModel.java',
            templateFile: 'plop-templates/adapter/acquiring/src/model/CustomExitStateModel.java'
          },
          // code.processor
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/AuthProcessor.java',
            templateFile: 'plop-templates/adapter/acquiring/src/processor/AuthProcessor.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/CancelProcessor.java',
            templateFile: 'plop-templates/adapter/acquiring/src/processor/CancelProcessor.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/CaptureProcessor.java',
            templateFile: 'plop-templates/adapter/acquiring/src/processor/CaptureProcessor.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/PreAuthProcessor.java',
            templateFile: 'plop-templates/adapter/acquiring/src/processor/PreAuthProcessor.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/RecurrentProcessor.java',
            templateFile: 'plop-templates/adapter/acquiring/src/processor/RecurrentProcessor.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/StatusProcessor.java',
            templateFile: 'plop-templates/adapter/acquiring/src/processor/StatusProcessor.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/processor/ErrorProcessor.java',
            templateFile: 'plop-templates/adapter/acquiring/src/processor/ErrorProcessor.java'
          },
          // code.converter.entry
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/entry/PaymentContextToEntryModelConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/entry/PaymentContextToEntryModelConverter.java'
          },
          // code.converter.exit
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/exit/ExitModelToProxyResultConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/exit/ExitModelToProxyResultConverter.java'
          },
          // code.converter.request
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToPreAuthRequestConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToPreAuthRequestConverter.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToAuthRequestConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToAuthRequestConverter.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToCaptureRequestConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToCaptureRequestConverter.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToCancelRequestConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToCancelRequestConverter.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToRecurrentRequestConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToRecurrentRequestConverter.java'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/converter/request/EntryStateToStatusRequestConverter.java',
            templateFile: 'plop-templates/adapter/acquiring/src/converter/request/EntryStateToStatusRequestConverter.java'
          },
          // code.servlet
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/servlet/Adapter{{properCase bank_name}}Servlet.java',
            templateFile: 'plop-templates/adapter/acquiring/src/servlet/AdapterServlet.java'
          },
          // code.provider
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/service/Adapter{{properCase bank_name}}ProviderProxy.java',
            templateFile: 'plop-templates/adapter/acquiring/src/service/AdapterProviderProxy.java'
          },
          // code.validator
          {
            type: 'add',
            path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/validator/ValidatorImpl.java',
            templateFile: 'plop-templates/adapter/acquiring/src/validator/ValidatorImpl.java'
          },
          // code.test
          {
            type: 'add',
            path: '{{pathHelper path}}src/test/java/com/rbkmoney/{{adapterAcquiringDirCase bank_name}}/Adapter{{properCase bank_name}}ApplicationTest.java',
            templateFile: 'plop-templates/adapter/acquiring/src/java-app-test.java'
          }
          );
      }

      actions.push(
        // infrastructure files
        {
          type: 'add',
          path: '{{pathHelper path}}src/test/resources/logback-test.xml',
          templateFile: 'plop-templates/adapter/acquiring/logback-test.xml'
        },
        {
          type: 'add',
          path: '{{pathHelper path}}Jenkinsfile',
          templateFile: 'plop-templates/adapter/acquiring/jenkinsfile'
        },
        {
          type: 'add',
          path: '{{pathHelper path}}.gitignore',
          templateFile: 'plop-templates/gitignore-template'
        },
        {
          type: 'add',
          path: '{{pathHelper path}}pom.xml',
          templateFile: 'plop-templates/adapter/acquiring/pom.xml'
        },
        // resources
        {
          type: 'add',
          path: '{{pathHelper path}}src/main/resources/application.yml',
          templateFile: 'plop-templates/adapter/acquiring/src/resources/spring.yml'
        },
        {
          type: 'add',
          path: '{{pathHelper path}}src/main/resources/fixture/errors.json',
          templateFile: 'plop-templates/adapter/acquiring/src/resources/error-mapping.json'
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
