# CompletableFuture Demo

Цей проєкт демонструє використання класу CompletableFuture у Java, зокрема методів thenCompose(), thenCombine(), allOf() та anyOf(). Програма моделює отримання відгуків про продукти з трьох різних платформ та їх асинхронну обробку.

## Особливості
- Паралельне виконання завдань: Отримання відгуків з трьох платформ одночасно.
- Об'єднання результатів: Використання thenCombine() для злиття результатів декількох завдань.
- Очікування завершення всіх завдань: Використання allOf() для гарантії завершення всіх завдань.
- Перше завершене завдання: Використання anyOf() для обробки першого виконаного завдання.

## Огляд коду

### Основні використані методи
1. `supplyAsync()`: Створює асинхронні завдання.
2. `allOf()`: Очікує завершення всіх завдань.
3. `anyOf()`: Обробляє результат першого завершеного завдання.
4. `thenCombine()`: Об'єднує результати двох завдань.
5. `thenRun()`: Виконує дію після завершення CompletableFuture.

### Симульований сценарій
1. Три платформи надають відгуки про продукт асинхронно.
2. Результати обробляються за допомогою методів CompletableFuture.
3. Об'єднані відгуки та результат першого завершеного завдання виводяться на екран.

### Приклад коду
CompletableFuture<String> platform1 = CompletableFuture.supplyAsync(() -> {
simulateDelay();
return "Відгуки з платформи 1";
});

CompletableFuture<String> platform2 = CompletableFuture.supplyAsync(() -> {
simulateDelay();
return "Відгуки з платформи 2";
});

CompletableFuture<String> platform3 = CompletableFuture.supplyAsync(() -> {
simulateDelay();
return "Відгуки з платформи 3";
});

CompletableFuture<Void> allReviews = CompletableFuture.allOf(platform1, platform2, platform3);
allReviews.thenRun(() -> {
try {
System.out.println(platform1.get());
System.out.println(platform2.get());
System.out.println(platform3.get());
} catch (Exception e) {
e.printStackTrace();
}
});

CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(platform1, platform2, platform3);
firstCompleted.thenAccept(result -> System.out.println("Перше завершене завдання: " + result));

## Як запустити
1. Вимоги:
    - Java Development Kit (JDK) 8 або вище.
2. Кроки:
    - Клонуйте репозиторій або скопіюйте код у ваш проєкт Java.
    - Скомпілюйте код за допомогою javac.
    - Запустіть програму за допомогою java CompletableFutureDemo.

## Очікуваний результат
1. Відгуки з трьох платформ:


Відгуки з платформи 1
Відгуки з платформи 2
Відгуки з платформи 3

2. Об'єднані результати:


Відгуки з платформи 1 + Відгуки з платформи 2 + Відгуки з платформи 3

3. Перше завершене завдання:


Перше завершене завдання: Відгуки з платформи X

