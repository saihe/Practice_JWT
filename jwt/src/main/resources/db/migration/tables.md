# init

* [トークン（token）](#token)

## user

ユーザーを保持するテーブル。

### user_columns

name|type|not null|default|key|auto increment|extra|comment
---|---|---|---|---|---|---|---
id|integer|||primary key autoincrement|||連番
login_id|text|y||unique|||ログインID。
password|text|y|||||符号化済みパスワード。
created_at|text|y|current_timestamp||||作成日時。
updated_at|text|y|current_timestamp on update current_timestamp||||更新日時。

## token

トークンを保持するテーブル。

### token_columns

name|type|not null|default|key|auto increment|extra|comment
---|---|---|---|---|---|---|---
id|integer|||primary key autoincrement|||連番
typ|text|y|JWT||||トークン種別。
alg|text|y|HS256||||暗号化アルゴリズム。
iss|text|y|||||発行者。
sub|text|y|||||件名。
name|text|y|||||名前。
iat|text|y|||||発行日時。
exp|text|y|||||無効日時。
created_at|text|y|current_timestamp||||作成日時。
updated_at|text|y|current_timestamp on update current_timestamp||||更新日時。
