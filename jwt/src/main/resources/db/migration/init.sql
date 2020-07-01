drop table if exists `user`;
create table `user` (
  `id` integer    primary key autoincrement comment '連番',
  `login_id` text not null   unique comment 'ログインID。',
  `password` text not null    comment '符号化済みパスワード。',
  `created_at` text not null default current_timestamp   comment '作成日時。',
  `updated_at` text not null default current_timestamp on update current_timestamp   comment '更新日時。'
) ENGINE = InnoDB DEFAULT CHARSET utf8 comment 'ユーザーを保持するテーブル。';
drop table if exists `token`;
create table `token` (
  `id` integer    primary key autoincrement comment '連番',
  `typ` text not null default JWT   comment 'トークン種別。',
  `alg` text not null default HS256   comment '暗号化アルゴリズム。',
  `iss` text not null    comment '発行者。',
  `sub` text not null    comment '件名。',
  `name` text not null    comment '名前。',
  `iat` text not null    comment '発行日時。',
  `exp` text not null    comment '無効日時。',
  `created_at` text not null default current_timestamp   comment '作成日時。',
  `updated_at` text not null default current_timestamp on update current_timestamp   comment '更新日時。'
) ENGINE = InnoDB DEFAULT CHARSET utf8 comment 'ユーザーを保持するテーブル。トークンを保持するテーブル。';
