# Installation

1. `brew install node` -- если установлен npm, то можно пропустить этот шаг
2. `npm install -g git+ssh://github.com/rbkmoney/rbk-templator.git#{last-commit-hash}` (последний номер коммита можно узнать в репозитории, либо в java канале)

# rbk-templator

Шаблонизатор для быстрого создания проектов на Java (возможна поддержка и других шаблонов)

#### Usage

1. Склонировать проект, в котором собираетесь использовать шаблон.
2. Запустить в консоли команду `rbk-templator`
3. Следовать указаниям

# wetkitty-templator

Скрипт для быстрой подготовки к выкатке в wetkitty

1. Проставляет для сервиса image-tag: id последнего коммита в master
2. Создает коммит с commit message последнего коммита feature-ветки
3. Создает ветку с таким же названием, как у feature-ветки
4. Пушит получившуюся ветку в wetkitty

#### Usage

1. Перейти в корневую директорию сервиса
2. Убедиться, что ваш сервис лежит в соседней директории с wetkitty:
```shell script
$ tree my-projects 
my-projects
├── my-service
└── wetkitty
``` 
3. Переключиться на feature-ветку
4. Запустить в консоли команду `wetkitty-templator`
