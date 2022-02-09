# Проект по автоматизации тестирования API для веб-приложения [Book Store](https://demoqa.com/books)

<p align="center">
<img width="50%" title="Сбермаркет" src="images/logo/Toolsqa.png">
</p>

## <img width="4%" title="Functional" src="images/logo/functional.png"> Покрытый функционал

### UI
- :white_check_mark: Запросы <code>GET</code>, <code>POST</code> и <code>DELETE</code>
    - :white_check_mark: Успешная генерация токена
    - :white_check_mark: Отображение списка всех книг
    - :white_check_mark: Отображение определенной книги по ISBN в списке всех книг
    - :white_check_mark: Добавление и удаление книги в профиле пользователя
- :white_check_mark: Отображение <code>statusCode</code> и <code>body</code> в ответе запроса
- :white_check_mark: Соответствие <code>body</code> ответа <code>JSON Schema</code>
- :white_check_mark: Отображение значений ключа в соответствии с проверкой на <code>Groovy</code>

## <img width="4%" title="Technologies" src="images/logo/technologies.png"> Технологический стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Allure TestOps" src="images/logo/Allure_TestOps.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
<img width="6%" title="Jira" src="images/logo/Jira.svg">
</p>

> В данном проекте автотесты написаны на <code>Java</code> с использованием библиотеки <code>REST Assured</code>
>
> <code>JUnit 5</code> используется для модульного тестирования
>
> <code>Gradle</code> используется для автоматизированной сборки проекта
>
> <code>Jenkins</code> выполняет запуск тестов
>
> <code>Allure Report</code> формирует отчет о запуске тестов
>
> Автотесты интегрируются с тест-менеджмент системой <code>Allure TestOps</code> и таск-трекер системой <code>Jira</code>
>
> В <code>Telegram</code> отправляются уведомления о пройденном прогоне

## <img width="4%" title="Jira" src="images/logo/ITerm2_v3_icon.png"> Запуск тестов из терминала

<details>
<summary>Подробнее</summary>

### :rocket: Локальный запуск тестов

```
gradle clean ${task}
```
> <details>
> <summary>:exclamation: Для запуска тестов необходимы файлы, в которых прописаны определенные параметры: </summary>
> <details>
> <summary> <code>api.properties</code> </summary>
>
> + <code>apiUrl</code> – адрес сервера, который будет использоваться в тестах
> </details>
>
> <details>
> <summary> <code>credentials.properties</code> </summary>
>
> + <code>userName</code> – логин пользователя для авторизации в приложении Book Store
> + <code>password</code> – пароль пользователя для авторизации в приложении Book Store
> </details>
> </details>

### :rocket: Параметры сборки

> <details>
> <summary><code>TASK</code> - список тестов, сгруппированных по параметру тега. В зависимости от выбранного параметра, будут запускаться определенные группы тестов</summary>
>
> + <code>test</code> - запуск всех тестов
> + <code>high_priority_tests</code> - запуск тестов с высоким приоритетом
> </details>

</details>

## <img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Формирование отчета Allure

<details>
<summary>Подробнее</summary>

> <details>
> <summary>:exclamation:</summary>
>
> + Предварительно необходимо установить _Allure_
> </details>

```
allure serve build/allure-results
```

</details>

## <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/Shalunov_BookStore_API/)

<details>
<summary>Подробнее</summary>

### :triangular_flag_on_post:     Для запуска тестов в Jenkins необходимо выполнить следующие шаги:

1. Открыть сборку [Jenkins](https://jenkins.autotests.cloud/job/Shalunov_BookStore_API)
2. Нажать на таск <code>"Собрать с параметрами"</code>
3. Указать [значения параметров](#rocket-параметры-сборки)
4. Нажать на кнопку <code>"Собрать"</code>

<p align="center">
<img title="Jenkins parameters" src="images/screens/jenkins_parameters.png">
</p>

### :triangular_flag_on_post: Для формирования отчета о прохождении тестов в Allure Report необходимо выполнить следующий шаг:

5. После выполнения сборки нажать на любую ссылку/иконку <code>"Allure Report"</code>

<p>
<img title="Allure Report" src="images/screens/jenkins_allure_report.png">
</p>

</details>

## <img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/Shalunov_BookStore_API/allure/)

<details>
<summary>Подробнее</summary>

> <code>Allure-framework</code> используется в качестве инструмента для построения отчетов о прогоне автотестов.
> Он позволяет получить информацию о ходе выполнения тестов, а также прикрепить скриншоты, логи и видео к формируемому отчету.
> Имеется возможность указать различные теги, приоритеты и прочую сопутствующую информацию для тестов.

### :dart: Главная страница Allure-отчета

<p align="center">
<img title="Allure Overview" src="images/screens/allure_overview.png">
</p>

### :dart: Информация о тестовом прогоне в графическом виде

<p align="center">
<img title="Allure Graphs" src="images/screens/allure_graphs.png">
</p>

### :dart: Группировка тестов по проверяемому функционалу

<p align="center">
<img title="Allure Behaviors" src="images/screens/allure_behaviors.png">
</p>

</details>

## <img width="4%" title="Allure TestOps" src="images/logo/Allure_TestOps.svg"> Интеграция тестов c тест-менеджмент системой [Allure TestOps](https://allure.autotests.cloud/project/1007/dashboards)

<details>
<summary>Подробнее</summary>

> <code>Allure TestOps</code> - это платформа управления качеством программного обеспечения, объединяющая автоматическое и ручное тестирование, которая позволяет управлять всем, что связано с тестированием, в одном месте.

### :test_tube:     Основной дашборд

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure_overview_dashboard.png">
</p>

### :test_tube:     Дашборд для отображения успешности и длительности тестов

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure_duration_and_success_rate_dashboard.png">
</p>

### :test_tube:     Дашборд по стендам

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure_stands_dashboard.png">
</p>

### :test_tube:     Дашборд по членам команды

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure_team_dashboard.png">
</p>

### :test_tube:     Запуски тестов

<p align="center">
<img title="Allure Launches" src="images/screens/allure_launches.png">
</p>

### :test_tube:     Результаты запуска тестов

<p align="center">
<img title="Allure Results" src="images/screens/allure_results.png">
</p>

### :test_tube: Сгруппированные тест-кейсы по проверяемому функционалу

<p align="center">
<img title="Allure Test Cases" src="images/screens/allure_testcases.png">
</p>

</details>

## <img width="4%" title="Jira" src="images/logo/Jira.svg"> Интеграция тестов c таск-трекер системой [Jira](https://jira.autotests.cloud/browse/HOMEWORK-326)

<details>
<summary>Подробнее</summary>

> Интеграция с <code>Jira</code> позволяет добавлять в задачи тест-кейсы, запуски и их результаты.

<p align="center">
<img title="Jira Issues" src="images/screens/jira_issues.png">
</p>

</details>

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

<details>
<summary>Подробнее</summary>

> Реализована отправка уведомлений о прогоне с помощью бота в <code>Telegram</code>.
> Фреймворк также поддерживает уведомления по _электронной почте, Slack, Skype_ и _Mattermost_.

<p align="center">
<img title="Telegram Notifications" src="images/screens/telegram_notifications.png">
</p>

</details>

<details>
<summary>:heartbeat: </summary>

### Спасибо за идею оформления [jjfhj](https://github.com/jjfhj)

</details>



