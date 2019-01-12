
-- 修改MySQL时区
set global time_zone = '+08:00'; -- 修改mysql全局时区为北京时间，即我们所在的东8区
set time_zone = '+08:00'; -- 修改当前会话时区
flush privileges; -- 立即生效
