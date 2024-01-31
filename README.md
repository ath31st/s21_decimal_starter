# s21_decimal starter

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SonarLint](https://img.shields.io/badge/SonarLint-CB2029?style=for-the-badge&logo=sonarlint&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

## Версия Программы

Текущая версия программы: 0.8.11

## Предисловие

Наверное, каждому знакома ситуация, когда стартуешь проект s21_decimal, читаешь задание, смотришь
лекции на ютубе, пир-ту-пиришься с командой, и вот, обязанности, объем работ распределен между
участниками, осталось только "Вошли и вышли, приключение на 20 минут". И, почти сразу, сталкиваешься
с проблемой - под рукой нет значений-заготовок для написания первых частей функций. Онлайн
конвертеры помогают лишь частино, да еще и отнимают много времени...

Данный стартер предназначен для того, чтобы облегчить тяжелый вход в нелегкий проект s21_decimal.
Пользуйтесь!

PS. Но помните, пользуясь данным стартером, вы прославляете:
<details>
  <summary>Нажмите, чтобы открыть спойлер</summary>

![image info](images/hb.png)
</details>

PSS. Приложение все еще находится на стадии разработки и тестирования. К возможным ошибкам, в том
числе и конвертацией чисел в s21_decimal, просьба отнестись с пониманием.

## Оглавление

1. [Версия Программы](#версия-программы)
2. [Предисловие](#предисловие)
3. [Список возможностей (меню)](#список-возможностей-меню)
4. [Пример работы](#пример-работы)
5. [Как запустить](#как-запустить)
6. [В планах](#в-планах)
7. [Список зависимостей (технологий и библиотек)](#список-зависимостей-технологий-и-библиотек)
8. [Лицензия](#лицензия)

## Список возможностей (меню)

    1. Сложение  - сложение двух десятичных чисел
    2. Вычитание - вычитание двух десятичных чисел
    3. Умножение - умножение двух десятичных чисел
    4. Деление - деление двух десятичных чисел
    5. Из десятичного в s21_decimal - перевод из десятичного вида в s21_decimal тип
    6. Генератор случайных s21_decimal - генерация выбранного количества s21_decimal значений
    7. Генератор unit-тестов - генерация выбранного количества unit-тестов для выбранной функции
        1. s21_add
        2. s21_sub
        3. s21_mul
        4. s21_div
        5. Все функции сразу в один файл
    8. Удаление папки и файлов с unit-тестами" - удаление сгенерированных файлов и папки, в которой они находились
    0. Выход - заверщение работы программы.

## Пример работы

![image info](images/example.gif)

## Как запустить

Вы можете скачать собранный бинарный
файл [здесь](https://github.com/ath31st/s21_decimal_starter/releases).</br>
Для запуска необходима Java не ниже 11 версии и команда ```java -jar s21_decimal_starter*.jar``` в
папке с файлом.

Так же, вы можете собрать бинарный файл самостоятельно.
На linux вам понадобится Java не ниже 11 версии и вот эти команды:

```bash
git clone https://github.com/ath31st/s21_decimal_starter
cd s21_decimal_starter
mvn clean package
cp target/*-jar-with-dependencies.jar s21_decimal_starter-$(mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec).jar
java -jar s21_decimal_starter*.jar
```

Для последующих запусков достаточно команды ```java -jar s21_decimal_starter*.jar``` в папке с
файлом.

## В планах

- Глобальный рефакторинг (в процессе)
- ~~Улучшение читаемости консольного интерфейса~~
- ~~Генерация фейл-тестов (слишком большой/слишком малый результат)~~ (добавлено в 0.8.11
  31.01.2024)
- ~~Вывод сгенерированных тестов в файл~~ (добавлено в 0.8.0 27.01.2024)
- Покрытие проекта тестами
- Покрытие проекта JavaDocs
- Оптимизация генерации тестовых кейсов

## Список зависимостей (технологий и библиотек)

1. Java: 11
2. Maven: 3.6.0
3. Apache Commons Lang 3: 3.14.0
4. Junit: 5.10.1
5. Log4j2: 2.22.1
6. Jacoco: 0.8.11

## Лицензия

Проект лицензирован по Apache 2.0 - см. [LICENSE](https://www.apache.org/licenses/LICENSE-2.0)