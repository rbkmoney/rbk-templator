module.exports = {
    description: 'Create a java service project structure',
    prompts: [
        {
            type: 'input',
            name: 'name',
            message: 'Как называется ваш проект?',
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
        actions.push({
            type: 'add',
            path: '{{pathHelper path}}src/main/kotlin/com/rbkmoney/{{packageCase name}}/{{properCase name}}Application.kt',
            templateFile: 'plop-templates/service/kotlin-app.kt'
          },
          {
            type: 'add',
            path: '{{pathHelper path}}src/test/kotlin/com/rbkmoney/{{packageCase name}}/{{properCase name}}ApplicationTest.kt',
            templateFile: 'plop-templates/service/kotlin-app-test.kt'
          });
      } else {
        actions.push({
          type: 'add',
          path: '{{pathHelper path}}src/main/java/com/rbkmoney/{{packageCase name}}/{{properCase name}}Application.java',
          templateFile: 'plop-templates/service/Application.java'
        },
        {
          type: 'add',
          path: '{{pathHelper path}}src/test/java/com/rbkmoney/{{packageCase name}}/{{properCase name}}ApplicationTest.java',
          templateFile: 'plop-templates/service/ApplicationTest.java'
        });
      }

      actions.push(
        {
          type: 'add',
          path: '{{pathHelper path}}src/main/resources/application.yml',
          templateFile: 'plop-templates/service/spring.yml'
        },
        {
          type: 'add',
          path: '{{pathHelper path}}src/test/resources/logback-test.xml',
          templateFile: 'plop-templates/service/service-logback.xml'
        },
        {
          type: 'add',
          path: '{{pathHelper path}}Jenkinsfile',
          templateFile: 'plop-templates/service/service-jenkinsfile'
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
          templateFile: 'plop-templates/service/service-pom.xml'
        }
      )

      return actions;
    }
};
