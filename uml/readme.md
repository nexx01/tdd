# attempt / System Design social network

## Functional requirements:
- 	анкеты людей (имя, описание, фото, город, интересы);
-	посты (описание, медиа, хэштеги, лайки, просмотры, комментарии);
-	личные сообщения и чаты (только текст и прочитанность сообщений); 
-   отношения (друзья, подписчики, любовные отношения);
-   медиа (фото, аудио, видео).


## Non-Functional requirements:
- 50 000 000 DAU
- Пользователь пишет 1 пост в день
- Пользователь читает 10 постов в день
- каждый пост 1 медиа
- длина поста 10 000символов
- пользователь в среднем имеет 100 друзей

## Capacity Estimate

RPS(write)=5*10^7/10^4=5 000r/s
RPS(read) =5*10^7/10^4 * 10 = 50 000r/s

### Traffic
Traffic(write) 5000 * 0.02мб * 1мб = 100мб/s
Traffic(read) 5000 * 0.02мб * 1мб = 100мб/s * 10 = 1GB/s

### Storage
100mb/s*86400 + 1GB/s * 86400 = 10PB/d *365*5= 20 000 PB/5year
200TB HHD or 50 TB SSD
- 4000HDD

