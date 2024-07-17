CarRentalProject
**Каральчук Олеся, УВП-211**
****
Учебная практика 2024. Реализация сервиса для аренды автомобилей премиум класса.

Бизнес-сценарии:
**Сценарий 1.** Аренда автомобиля с дополнительными услугами
– Клиент выбирает машину и может добавить доп. услуги (украшение автомобиля лентами и шариками, детское кресло, личный водитель, страхование, WiFI в автомобиле);
– Система проверяет то, что данная машина свободна на данный промежуток времени и доступна для аренды;
– Если клиент добавляет доп. услуги итоговая цена аренды увеличивается;
– Если клиент решает не оплачивать, то аренда становится недействительной и платеж меняет статус на «отменен»;
– Если клиент производит оплату, статус платежа меняется на «завершено».  Система создает запись об аренде со всей информацией о выбранных услугах, клиенте, автомобиле.
**Сценарий 2.** Поиск автомобиля на определенные даты
– Клиент указывает интересующие его даты для аренды машины. 
– Система фильтрует автомобили и отображает список автомобилей, доступных в выбранные дни.
**Сценарий 3.** Выбор доступных автомобилей по критериям, интересующих пользователя
– Клиент указывает необходимые критерии для поиска автомобиля. В системе доступен поиск по марке, модели, типу, цвету и стоимости;
– Система фильтрует автомобили и отображает список автомобилей по критериям.


**Диаграмма классов**
![CarRental](https://github.com/user-attachments/assets/14ef1eec-a2ca-4f1f-9742-fc5adee04481)



